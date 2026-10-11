# Rodada 2 — Resposta da IA: ajustes no desenho e os testes

Resposta recebida na íntegra, sem edição. Os arquivos de teste de `src/test/java` foram extraídos daqui, sem nenhuma alteração.

---

# Ajustes no desenho e testes de especificação (JUnit 5)

## 1. Os dois ajustes

**Ajuste 1: a cota não pode mais ser pulada.** A assinatura passa a ser:

```java
Reserva reservar(Matricula aluno, Periodo periodo, int tamanhoGrupo,
                 CotaDiariaDoAluno cota, LocalDateTime agora)
```

Dentro de `AgendaDaSala.reservar`, nesta ordem:

1. Verifica se o período é do dia da agenda e se a cota é do mesmo aluno e do mesmo dia. Se não for, lança `IllegalArgumentException`, porque isso é erro de uso da API e não regra de negócio.
2. Chama `cota.garantirQueCabe(periodo)`.
3. Verifica conflito de horário.
4. Chama o construtor package-private de `Reserva`, que verifica capacidade e passado.
5. Só depois de tudo isso registra a reserva nova na agenda e na cota, com `cota.registrar(reserva)`, que também é package-private.

Por causa do passo 5, usar a mesma cota em duas reservas acumula as horas, e uma reserva recusada não deixa rastro.

Uma ressalva honesta: a agenda consegue conferir que a cota é do aluno e do dia certos. Ela não consegue conferir que a lista de reservas da cota está completa. Isso continua sendo responsabilidade do repositório, assim como a lista de reservas da própria agenda.

**Ajuste 2: a busca agora depende da sala, e não o contrário.** `Sala.atende` sai e entra:

```java
// em CriterioDeBusca
public boolean aceita(Sala sala) {
    return sala.comporta(capacidadeMinima)
        && (!exigeProjetor || sala.recursos().projetor());
}
```

**Premissas.** A premissa 3 entra como **"Regra acrescentada"** nos `@DisplayName`. As premissas 6 e 7 não aparecem nos testes.

## 2. O que os testes fixam

Os testes travam os nomes de pacotes, classes, construtores e métodos abaixo. O código de produção vai ter de seguir exatamente isso.

| Pacote | Tipo | API usada pelos testes |
|---|---|---|
| `reservas.dominio` | `record Periodo(LocalDate data, int horaInicio, int duracaoHoras)` | `inicio()`, `fim()`, `horas()`, `sobrepoe(Periodo)`, `jaComecouEm(LocalDateTime)` |
| | `record Matricula(String valor)`, `record Recursos(boolean projetor, boolean lousaDigital)`, `record ReservaId(UUID valor)` | |
| | `record CriterioDeBusca(Periodo periodo, int capacidadeMinima, boolean exigeProjetor)` | `aceita(Sala)` |
| | `final class Sala(int numero, int capacidade, Recursos recursos)` | `numero()`, `capacidade()`, `recursos()`, `comporta(int)`, `equals` pelo número |
| | `Reserva` (construtor package-private) | `id()`, `sala()`, `aluno()`, `periodo()`, `tamanhoGrupo()`, `status()`, `estaAtiva()`, `horas()`, `ocupa(Periodo)`, `cancelar(Matricula, LocalDateTime)` |
| | `enum StatusReserva { ATIVA, CANCELADA }` | |
| | `AgendaDaSala(Sala, LocalDate, List<Reserva>)` | `estaLivre(Periodo)`, `reservar(...)` como acima |
| | `CotaDiariaDoAluno(Matricula, LocalDate, List<Reserva>)` | `LIMITE_HORAS`, `horasUsadas()`, `garantirQueCabe(Periodo)` |
| | `RepositorioDeReservas` (interface) | `salvar`, `buscarPorId` (devolve `Optional`), `ativasDaSala(int, LocalDate)`, `ativasDoAluno(Matricula, LocalDate)` |
| | `CatalogoDeSalas()` | `cadastrar(Sala)` |
| | `RegraVioladaException extends RuntimeException` | Precisa estender `RuntimeException` **diretamente**, e não `IllegalArgumentException`. Se estendesse, os testes que esperam `IllegalArgumentException` passariam por engano. |
| `reservas.infra` | `RepositorioDeReservasEmMemoria()` | |
| `reservas.aplicacao` | `CentralDeReservas(Clock, RepositorioDeReservas, CatalogoDeSalas)` | `reservar(Matricula, int numeroSala, Periodo, int grupo)`, `cancelar(ReservaId, Matricula)`, `buscarSalasLivres(CriterioDeBusca)` |

Os construtores de `AgendaDaSala` e `CotaDiariaDoAluno` precisam fazer **cópia defensiva** da lista recebida, porque os testes passam `List.of(...)`, que é imutável.

Para rodar, a única dependência é `org.junit.jupiter:junit-jupiter` (5.10.x, que já inclui `junit-jupiter-params`), mais o `maven-surefire-plugin` 3.x.

## 3. Os testes

### `src/test/java/reservas/dominio/Exemplos.java`

```java
package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/** Dados e atalhos compartilhados pelos testes de domínio. */
final class Exemplos {

    static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    /** Um instante anterior a qualquer horário de DIA. */
    static final LocalDateTime BEM_ANTES = LocalDateTime.of(2026, 3, 9, 12, 0);

    static final Matricula ANA = new Matricula("RA001");
    static final Matricula BETO = new Matricula("RA002");

    private Exemplos() {
    }

    static Sala sala(int numero, int capacidade) {
        return new Sala(numero, capacidade, new Recursos(false, false));
    }

    static Periodo periodo(int hora, int duracao) {
        return new Periodo(DIA, hora, duracao);
    }

    static AgendaDaSala agendaVazia(Sala sala) {
        return new AgendaDaSala(sala, DIA, List.of());
    }

    static CotaDiariaDoAluno cotaVazia(Matricula aluno) {
        return new CotaDiariaDoAluno(aluno, DIA, List.of());
    }

    /** Cria uma reserva válida pelo único caminho público: a agenda da sala. */
    static Reserva reservaDe(Matricula aluno, Sala sala, int hora, int duracao) {
        return agendaVazia(sala).reservar(aluno, periodo(hora, duracao), 1, cotaVazia(aluno), BEM_ANTES);
    }
}
```

### `src/test/java/reservas/dominio/PeriodoTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reservas.dominio.Exemplos.DIA;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Periodo")
class PeriodoTest {

    @ParameterizedTest(name = "[{index}] início às {0}h com {1}h de duração")
    @CsvSource({"8, 1", "8, 2", "14, 1", "20, 2", "21, 1"})
    @DisplayName("Regra: as salas funcionam das 8h às 22h (períodos dentro do horário são aceitos)")
    void aceitaPeriodoDentroDoHorario(int hora, int duracao) {
        Periodo periodo = new Periodo(DIA, hora, duracao);

        assertEquals(DIA.atTime(hora, 0), periodo.inicio());
        assertEquals(DIA.atTime(hora + duracao, 0), periodo.fim());
    }

    @ParameterizedTest(name = "[{index}] início às {0}h com {1}h de duração")
    @CsvSource({"7, 1", "6, 2", "21, 2", "22, 1", "23, 1", "0, 1"})
    @DisplayName("Regra: as salas funcionam das 8h às 22h (períodos fora do horário são recusados)")
    void recusaPeriodoForaDoHorario(int hora, int duracao) {
        assertThrows(RegraVioladaException.class, () -> new Periodo(DIA, hora, duracao));
    }

    @ParameterizedTest(name = "[{index}] duração de {0}h")
    @ValueSource(ints = {1, 2})
    @DisplayName("Regra: a reserva dura de 1 a 2 horas seguidas (durações válidas)")
    void aceitaDuracaoDeUmaOuDuasHoras(int duracao) {
        assertEquals(duracao, new Periodo(DIA, 10, duracao).horas());
    }

    @ParameterizedTest(name = "[{index}] duração de {0}h")
    @ValueSource(ints = {0, 3, -1})
    @DisplayName("Regra: a reserva dura de 1 a 2 horas seguidas (durações inválidas são recusadas)")
    void recusaDuracaoForaDoIntervalo(int duracao) {
        assertThrows(RegraVioladaException.class, () -> new Periodo(DIA, 10, duracao));
    }

    @ParameterizedTest(name = "[{index}] início às {0}h")
    @ValueSource(ints = {8, 13, 21})
    @DisplayName("Regra: as reservas são em horas cheias (início e fim sem minutos nem segundos)")
    void inicioEFimSaoHorasCheias(int hora) {
        Periodo periodo = new Periodo(DIA, hora, 1);

        assertEquals(0, periodo.inicio().getMinute());
        assertEquals(0, periodo.inicio().getSecond());
        assertEquals(0, periodo.fim().getMinute());
        assertEquals(0, periodo.fim().getSecond());
    }

    @ParameterizedTest(name = "[{index}] 10h–12h contra início às {0}h com {1}h: sobrepõe = {2}")
    @CsvSource({
        "8,  2, false",
        "9,  1, false",
        "9,  2, true",
        "10, 1, true",
        "10, 2, true",
        "11, 1, true",
        "11, 2, true",
        "12, 1, false",
        "12, 2, false"})
    @DisplayName("Regra de apoio: dois períodos se sobrepõem quando dividem ao menos uma hora (fim exclusivo)")
    void sobreposicao(int hora, int duracao, boolean esperado) {
        Periodo dezAsDoze = new Periodo(DIA, 10, 2);
        Periodo outro = new Periodo(DIA, hora, duracao);

        assertEquals(esperado, dezAsDoze.sobrepoe(outro));
        assertEquals(esperado, outro.sobrepoe(dezAsDoze), "a sobreposição deve ser simétrica");
    }

    @Test
    @DisplayName("Regra de apoio: períodos em dias diferentes nunca se sobrepõem")
    void diasDiferentesNaoSeSobrepoem() {
        assertFalse(new Periodo(DIA, 10, 2).sobrepoe(new Periodo(DIA.plusDays(1), 10, 2)));
    }

    @ParameterizedTest(name = "[{index}] agora = {0}: já começou = {1}")
    @CsvSource({
        "2026-03-09T23:00,    false",
        "2026-03-10T13:59:59, false",
        "2026-03-10T14:00,    true",
        "2026-03-10T14:30,    true",
        "2026-03-11T08:00,    true"})
    @DisplayName("Regra de apoio: um período já começou a partir do instante exato do seu início")
    void jaComecouEm(LocalDateTime agora, boolean esperado) {
        assertEquals(esperado, new Periodo(DIA, 14, 1).jaComecouEm(agora));
    }
}
```

### `src/test/java/reservas/dominio/SalaTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Sala")
class SalaTest {

    @Test
    @DisplayName("Regra: a sala tem número, capacidade e pode ter projetor e/ou lousa digital")
    void guardaSeusDados() {
        Sala sala = new Sala(101, 6, new Recursos(true, false));

        assertEquals(101, sala.numero());
        assertEquals(6, sala.capacidade());
        assertTrue(sala.recursos().projetor());
        assertFalse(sala.recursos().lousaDigital());
    }

    @ParameterizedTest(name = "[{index}] capacidade {0}")
    @ValueSource(ints = {0, -1})
    @DisplayName("Regra de apoio: a capacidade da sala é positiva")
    void recusaCapacidadeNaoPositiva(int capacidade) {
        assertThrows(RegraVioladaException.class,
                () -> new Sala(101, capacidade, new Recursos(false, false)));
    }

    @ParameterizedTest(name = "[{index}] grupo de {0} numa sala de 6: comporta = {1}")
    @CsvSource({"1, true", "5, true", "6, true", "7, false"})
    @DisplayName("Regra: o tamanho do grupo não pode passar da capacidade da sala")
    void comporta(int grupo, boolean esperado) {
        Sala sala = new Sala(101, 6, new Recursos(false, false));

        assertEquals(esperado, sala.comporta(grupo));
    }

    @Test
    @DisplayName("Regra de apoio: salas com o mesmo número são a mesma sala")
    void identidadePeloNumero() {
        assertEquals(new Sala(101, 6, new Recursos(false, false)),
                     new Sala(101, 10, new Recursos(true, true)));
    }
}
```

### `src/test/java/reservas/dominio/CriterioDeBuscaTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reservas.dominio.Exemplos.periodo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("CriterioDeBusca")
class CriterioDeBuscaTest {

    @ParameterizedTest(name = "[{index}] sala({0} lugares, projetor={1}) x busca(mín. {2}, exige projetor={3}) → {4}")
    @CsvSource({
        "6, false, 6, false, true",
        "6, false, 7, false, false",
        "6, false, 1, true,  false",
        "6, true,  1, true,  true",
        "6, true,  1, false, true",
        "6, true,  7, true,  false"})
    @DisplayName("Regra: a busca exige capacidade mínima e, opcionalmente, projetor")
    void aceita(int capacidade, boolean projetor, int minima, boolean exigeProjetor, boolean esperado) {
        Sala sala = new Sala(101, capacidade, new Recursos(projetor, false));
        CriterioDeBusca criterio = new CriterioDeBusca(periodo(10, 1), minima, exigeProjetor);

        assertEquals(esperado, criterio.aceita(sala));
    }

    @ParameterizedTest(name = "[{index}] capacidade mínima {0}")
    @ValueSource(ints = {0, -1})
    @DisplayName("Regra de apoio: a capacidade mínima da busca é de pelo menos 1 pessoa")
    void recusaCapacidadeMinimaInvalida(int minima) {
        assertThrows(RegraVioladaException.class,
                () -> new CriterioDeBusca(periodo(10, 1), minima, false));
    }
}
```

### `src/test/java/reservas/dominio/ReservaTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reservas.dominio.Exemplos.ANA;
import static reservas.dominio.Exemplos.BEM_ANTES;
import static reservas.dominio.Exemplos.BETO;
import static reservas.dominio.Exemplos.periodo;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Reserva")
class ReservaTest {

    private final Sala salaDeSeis = Exemplos.sala(101, 6);

    private Reserva reservar(int grupo, Periodo periodo, LocalDateTime agora) {
        AgendaDaSala agenda = new AgendaDaSala(salaDeSeis, periodo.data(), List.of());
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(ANA, periodo.data(), List.of());
        return agenda.reservar(ANA, periodo, grupo, cota, agora);
    }

    private Reserva reservaDaAnaAsQuatorze() {
        return reservar(2, periodo(14, 1), BEM_ANTES);
    }

    @Nested
    @DisplayName("Criação")
    class Criacao {

        @Test
        @DisplayName("Uma reserva nova nasce ativa, com identificador próprio e com os dados informados")
        void nasceAtiva() {
            Reserva reserva = reservar(3, periodo(14, 1), BEM_ANTES);

            assertNotNull(reserva.id());
            assertEquals(StatusReserva.ATIVA, reserva.status());
            assertTrue(reserva.estaAtiva());
            assertEquals(salaDeSeis, reserva.sala());
            assertEquals(ANA, reserva.aluno());
            assertEquals(periodo(14, 1), reserva.periodo());
            assertEquals(3, reserva.tamanhoGrupo());
            assertEquals(1, reserva.horas());
            assertTrue(reserva.ocupa(periodo(14, 1)));
            assertNotEquals(reserva.id(), reservar(3, periodo(16, 1), BEM_ANTES).id());
        }

        @ParameterizedTest(name = "[{index}] grupo de {0} numa sala de 6")
        @ValueSource(ints = {1, 6})
        @DisplayName("Regra: o grupo pode ter até a capacidade da sala (grupo igual à capacidade é aceito)")
        void aceitaGrupoAteACapacidade(int grupo) {
            assertEquals(grupo, reservar(grupo, periodo(14, 1), BEM_ANTES).tamanhoGrupo());
        }

        @ParameterizedTest(name = "[{index}] grupo de {0} numa sala de 6")
        @ValueSource(ints = {7, 20})
        @DisplayName("Regra: o grupo não pode passar da capacidade da sala (uma pessoa a mais é recusada)")
        void recusaGrupoAcimaDaCapacidade(int grupo) {
            assertThrows(RegraVioladaException.class, () -> reservar(grupo, periodo(14, 1), BEM_ANTES));
        }

        @ParameterizedTest(name = "[{index}] grupo de {0}")
        @ValueSource(ints = {0, -1})
        @DisplayName("Regra de apoio: o grupo tem pelo menos uma pessoa")
        void recusaGrupoVazio(int grupo) {
            assertThrows(RegraVioladaException.class, () -> reservar(grupo, periodo(14, 1), BEM_ANTES));
        }

        @ParameterizedTest(name = "[{index}] reservar 14h quando agora = {0}")
        @ValueSource(strings = {"2026-03-09T23:00", "2026-03-10T08:00", "2026-03-10T13:59:59"})
        @DisplayName("Regra: não se pode reservar no passado (antes do início é aceito, inclusive no mesmo dia)")
        void aceitaAntesDoInicio(LocalDateTime agora) {
            assertTrue(reservar(1, periodo(14, 1), agora).estaAtiva());
        }

        @ParameterizedTest(name = "[{index}] reservar 14h quando agora = {0}")
        @ValueSource(strings = {"2026-03-10T14:00", "2026-03-10T14:30", "2026-03-11T09:00"})
        @DisplayName("Regra: não se pode reservar no passado (período já iniciado é recusado)")
        void recusaPeriodoJaIniciado(LocalDateTime agora) {
            assertThrows(RegraVioladaException.class, () -> reservar(1, periodo(14, 1), agora));
        }
    }

    @Nested
    @DisplayName("Cancelamento")
    class Cancelamento {

        @Test
        @DisplayName("Regra: cancelar exatamente 1 hora antes do início é permitido")
        void cancelaExatamenteUmaHoraAntes() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            reserva.cancelar(ANA, LocalDateTime.parse("2026-03-10T13:00:00"));

            assertEquals(StatusReserva.CANCELADA, reserva.status());
            assertFalse(reserva.estaAtiva());
        }

        @Test
        @DisplayName("Regra: um segundo depois do limite de 1 hora antes, o cancelamento é recusado")
        void recusaUmSegundoDepoisDoLimite() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            assertThrows(RegraVioladaException.class,
                    () -> reserva.cancelar(ANA, LocalDateTime.parse("2026-03-10T13:00:01")));
            assertEquals(StatusReserva.ATIVA, reserva.status());
        }

        @ParameterizedTest(name = "[{index}] cancelar a reserva das 14h quando agora = {0}")
        @ValueSource(strings = {"2026-03-09T20:00", "2026-03-10T08:00", "2026-03-10T12:59:59"})
        @DisplayName("Regra: com mais de 1 hora de antecedência, o aluno pode cancelar")
        void cancelaComAntecedencia(LocalDateTime agora) {
            Reserva reserva = reservaDaAnaAsQuatorze();

            reserva.cancelar(ANA, agora);

            assertEquals(StatusReserva.CANCELADA, reserva.status());
        }

        @ParameterizedTest(name = "[{index}] cancelar a reserva das 14h quando agora = {0}")
        @ValueSource(strings = {"2026-03-10T13:30", "2026-03-10T14:00", "2026-03-10T14:30", "2026-03-11T09:00"})
        @DisplayName("Regra: com menos de 1 hora de antecedência, ou depois do início, não se pode cancelar")
        void recusaCancelamentoTardio(LocalDateTime agora) {
            Reserva reserva = reservaDaAnaAsQuatorze();

            assertThrows(RegraVioladaException.class, () -> reserva.cancelar(ANA, agora));
            assertEquals(StatusReserva.ATIVA, reserva.status());
        }

        @Test
        @DisplayName("Regra acrescentada: só o aluno dono da reserva pode cancelá-la")
        void soODonoCancela() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            assertThrows(RegraVioladaException.class, () -> reserva.cancelar(BETO, BEM_ANTES));
            assertEquals(StatusReserva.ATIVA, reserva.status());
        }

        @Test
        @DisplayName("Regra de apoio: uma reserva cancelada não pode ser cancelada de novo")
        void naoCancelaDuasVezes() {
            Reserva reserva = reservaDaAnaAsQuatorze();
            reserva.cancelar(ANA, BEM_ANTES);

            assertThrows(RegraVioladaException.class, () -> reserva.cancelar(ANA, BEM_ANTES));
        }

        @Test
        @DisplayName("Regra de apoio: uma reserva cancelada não ocupa horário nem consome horas")
        void canceladaNaoOcupaNemConsome() {
            Reserva reserva = reservaDaAnaAsQuatorze();
            reserva.cancelar(ANA, BEM_ANTES);

            assertFalse(reserva.ocupa(periodo(14, 1)));
            assertEquals(0, reserva.horas());
        }
    }
}
```

### `src/test/java/reservas/dominio/AgendaDaSalaTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reservas.dominio.Exemplos.ANA;
import static reservas.dominio.Exemplos.BEM_ANTES;
import static reservas.dominio.Exemplos.BETO;
import static reservas.dominio.Exemplos.DIA;
import static reservas.dominio.Exemplos.agendaVazia;
import static reservas.dominio.Exemplos.cotaVazia;
import static reservas.dominio.Exemplos.periodo;
import static reservas.dominio.Exemplos.reservaDe;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("AgendaDaSala")
class AgendaDaSalaTest {

    private final Sala sala101 = Exemplos.sala(101, 6);
    private final Sala sala102 = Exemplos.sala(102, 6);

    private AgendaDaSala agendaComAnaDasDezAsDoze() {
        return new AgendaDaSala(sala101, DIA, List.of(reservaDe(ANA, sala101, 10, 2)));
    }

    @ParameterizedTest(name = "[{index}] sala ocupada 10h–12h; pedido às {0}h por {1}h")
    @CsvSource({"9, 2", "10, 1", "10, 2", "11, 1", "11, 2"})
    @DisplayName("Regra: uma sala não pode ter duas reservas no mesmo horário (horários sobrepostos são recusados)")
    void recusaHorarioSobreposto(int hora, int duracao) {
        AgendaDaSala agenda = agendaComAnaDasDezAsDoze();

        assertFalse(agenda.estaLivre(periodo(hora, duracao)));
        assertThrows(RegraVioladaException.class,
                () -> agenda.reservar(BETO, periodo(hora, duracao), 1, cotaVazia(BETO), BEM_ANTES));
    }

    @ParameterizedTest(name = "[{index}] sala ocupada 10h–12h; pedido às {0}h por {1}h")
    @CsvSource({"8, 2", "9, 1", "12, 1", "12, 2", "21, 1"})
    @DisplayName("Regra: uma sala não pode ter duas reservas no mesmo horário (horários vizinhos são aceitos)")
    void aceitaHorarioLivre(int hora, int duracao) {
        AgendaDaSala agenda = agendaComAnaDasDezAsDoze();

        assertTrue(agenda.estaLivre(periodo(hora, duracao)));
        Reserva reserva = agenda.reservar(BETO, periodo(hora, duracao), 1, cotaVazia(BETO), BEM_ANTES);
        assertTrue(reserva.estaAtiva());
        assertEquals(sala101, reserva.sala());
    }

    @Test
    @DisplayName("Regra: a reserva cancelada não conta para o conflito de horário")
    void canceladaNaoContaParaConflito() {
        Reserva cancelada = reservaDe(ANA, sala101, 10, 2);
        cancelada.cancelar(ANA, BEM_ANTES);
        AgendaDaSala agenda = new AgendaDaSala(sala101, DIA, List.of(cancelada));

        assertTrue(agenda.estaLivre(periodo(10, 2)));
        assertDoesNotThrow(() -> agenda.reservar(BETO, periodo(10, 2), 1, cotaVazia(BETO), BEM_ANTES));
    }

    @Test
    @DisplayName("Regra de apoio: a agenda passa a considerar ocupada a reserva que ela acabou de criar")
    void registraAReservaCriada() {
        AgendaDaSala agenda = agendaVazia(sala101);
        agenda.reservar(ANA, periodo(10, 1), 1, cotaVazia(ANA), BEM_ANTES);

        assertFalse(agenda.estaLivre(periodo(10, 1)));
        assertThrows(RegraVioladaException.class,
                () -> agenda.reservar(BETO, periodo(10, 1), 1, cotaVazia(BETO), BEM_ANTES));
    }

    @Test
    @DisplayName("Regra: a agenda aplica a cota de 2 horas por dia; não há como criar reserva sem essa checagem")
    void agendaAplicaACota() {
        Reserva duasHorasEmOutraSala = reservaDe(ANA, sala102, 8, 2);
        CotaDiariaDoAluno cotaCheia = new CotaDiariaDoAluno(ANA, DIA, List.of(duasHorasEmOutraSala));
        AgendaDaSala agenda = agendaVazia(sala101);

        assertThrows(RegraVioladaException.class,
                () -> agenda.reservar(ANA, periodo(14, 1), 1, cotaCheia, BEM_ANTES));
        assertTrue(agenda.estaLivre(periodo(14, 1)), "a reserva recusada não pode ficar registrada");
    }

    @Test
    @DisplayName("Regra: a cota soma todas as reservas do dia, mesmo quando a mesma cota é usada em várias agendas")
    void cotaCompartilhadaAcumula() {
        CotaDiariaDoAluno cota = cotaVazia(ANA);
        agendaVazia(sala101).reservar(ANA, periodo(8, 2), 1, cota, BEM_ANTES);

        assertEquals(2, cota.horasUsadas());
        assertThrows(RegraVioladaException.class,
                () -> agendaVazia(sala102).reservar(ANA, periodo(14, 1), 1, cota, BEM_ANTES));
    }

    @Test
    @DisplayName("Regra de apoio: uma reserva recusada por capacidade não ocupa a agenda nem a cota")
    void recusaPorCapacidadeNaoDeixaRastro() {
        AgendaDaSala agenda = agendaVazia(sala101);
        CotaDiariaDoAluno cota = cotaVazia(ANA);

        assertThrows(RegraVioladaException.class,
                () -> agenda.reservar(ANA, periodo(14, 1), 7, cota, BEM_ANTES));
        assertTrue(agenda.estaLivre(periodo(14, 1)));
        assertEquals(0, cota.horasUsadas());
    }

    @Test
    @DisplayName("Uso indevido: período de outro dia é recusado pela agenda")
    void recusaPeriodoDeOutroDia() {
        AgendaDaSala agenda = agendaVazia(sala101);
        Periodo amanha = new Periodo(DIA.plusDays(1), 10, 1);

        assertThrows(IllegalArgumentException.class,
                () -> agenda.reservar(ANA, amanha, 1, cotaVazia(ANA), BEM_ANTES));
    }

    @Test
    @DisplayName("Uso indevido: cota de outro aluno é recusada pela agenda")
    void recusaCotaDeOutroAluno() {
        AgendaDaSala agenda = agendaVazia(sala101);

        assertThrows(IllegalArgumentException.class,
                () -> agenda.reservar(ANA, periodo(10, 1), 1, cotaVazia(BETO), BEM_ANTES));
    }

    @Test
    @DisplayName("Uso indevido: cota de outro dia é recusada pela agenda")
    void recusaCotaDeOutroDia() {
        AgendaDaSala agenda = agendaVazia(sala101);
        CotaDiariaDoAluno cotaDeAmanha = new CotaDiariaDoAluno(ANA, DIA.plusDays(1), List.of());

        assertThrows(IllegalArgumentException.class,
                () -> agenda.reservar(ANA, periodo(10, 1), 1, cotaDeAmanha, BEM_ANTES));
    }
}
```

### `src/test/java/reservas/dominio/CotaDiariaDoAlunoTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reservas.dominio.Exemplos.ANA;
import static reservas.dominio.Exemplos.BEM_ANTES;
import static reservas.dominio.Exemplos.DIA;
import static reservas.dominio.Exemplos.cotaVazia;
import static reservas.dominio.Exemplos.periodo;
import static reservas.dominio.Exemplos.reservaDe;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("CotaDiariaDoAluno")
class CotaDiariaDoAlunoTest {

    private final Sala sala101 = Exemplos.sala(101, 6);
    private final Sala sala102 = Exemplos.sala(102, 6);

    @Test
    @DisplayName("Regra: o limite diário é de 2 horas")
    void limiteEDuasHoras() {
        assertEquals(2, CotaDiariaDoAluno.LIMITE_HORAS);
        assertEquals(0, cotaVazia(ANA).horasUsadas());
    }

    @ParameterizedTest(name = "[{index}] {0}h já usadas + {1}h novas: cabe = {2}")
    @CsvSource({
        "0, 1, true",
        "0, 2, true",
        "1, 1, true",
        "1, 2, false",
        "2, 1, false"})
    @DisplayName("Regra: cada aluno reserva no máximo 2 horas por dia, somando todas as reservas")
    void limiteDiario(int usadas, int novas, boolean cabe) {
        List<Reserva> existentes = usadas == 0 ? List.of() : List.of(reservaDe(ANA, sala102, 8, usadas));
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(ANA, DIA, existentes);

        assertEquals(usadas, cota.horasUsadas());
        if (cabe) {
            assertDoesNotThrow(() -> cota.garantirQueCabe(periodo(14, novas)));
        } else {
            assertThrows(RegraVioladaException.class, () -> cota.garantirQueCabe(periodo(14, novas)));
        }
    }

    @Test
    @DisplayName("Regra: 1h + 1h em salas diferentes cabe na cota")
    void umaMaisUmaCabe() {
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(ANA, DIA, List.of(reservaDe(ANA, sala101, 8, 1)));

        assertDoesNotThrow(() -> cota.garantirQueCabe(periodo(14, 1)));
    }

    @Test
    @DisplayName("Regra: duas reservas de 1h esgotam a cota; uma terceira é recusada")
    void duasReservasDeUmaHoraEsgotam() {
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(ANA, DIA, List.of(
                reservaDe(ANA, sala101, 8, 1),
                reservaDe(ANA, sala102, 10, 1)));

        assertEquals(2, cota.horasUsadas());
        assertThrows(RegraVioladaException.class, () -> cota.garantirQueCabe(periodo(14, 1)));
    }

    @Test
    @DisplayName("Regra: a reserva cancelada não conta para a cota diária")
    void canceladaNaoConta() {
        Reserva cancelada = reservaDe(ANA, sala101, 8, 2);
        cancelada.cancelar(ANA, BEM_ANTES);
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(ANA, DIA, List.of(cancelada));

        assertEquals(0, cota.horasUsadas());
        assertDoesNotThrow(() -> cota.garantirQueCabe(periodo(14, 2)));
    }

    @Test
    @DisplayName("Regra de apoio: apenas verificar não consome a cota")
    void verificarNaoConsome() {
        CotaDiariaDoAluno cota = cotaVazia(ANA);

        cota.garantirQueCabe(periodo(14, 2));

        assertEquals(0, cota.horasUsadas());
        assertDoesNotThrow(() -> cota.garantirQueCabe(periodo(14, 2)));
    }
}
```

### `src/test/java/reservas/aplicacao/CentralDeReservasTest.java`

```java
package reservas.aplicacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.Sala;
import reservas.dominio.StatusReserva;
import reservas.infra.RepositorioDeReservasEmMemoria;

@DisplayName("CentralDeReservas")
class CentralDeReservasTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    private static final LocalDateTime SETE_DA_MANHA = DIA.atTime(7, 0);
    private static final Matricula ANA = new Matricula("RA001");
    private static final Matricula BETO = new Matricula("RA002");

    private RepositorioDeReservas repositorio;
    private CatalogoDeSalas catalogo;

    @BeforeEach
    void prepararSalas() {
        repositorio = new RepositorioDeReservasEmMemoria();
        catalogo = new CatalogoDeSalas();
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));
        catalogo.cadastrar(new Sala(103, 10, new Recursos(false, true)));
        catalogo.cadastrar(new Sala(104, 6, new Recursos(true, true)));
    }

    /** Cada "momento" é uma central com o relógio parado; repositório e catálogo são compartilhados. */
    private CentralDeReservas centralEm(LocalDateTime agora) {
        Clock relogio = Clock.fixed(agora.atZone(FUSO).toInstant(), FUSO);
        return new CentralDeReservas(relogio, repositorio, catalogo);
    }

    private static Periodo periodo(int hora, int duracao) {
        return new Periodo(DIA, hora, duracao);
    }

    private static Set<Integer> numeros(String lista) {
        return lista.isBlank()
                ? Set.of()
                : Arrays.stream(lista.trim().split("\\s+")).map(Integer::valueOf).collect(Collectors.toSet());
    }

    @Nested
    @DisplayName("Ao reservar")
    class AoReservar {

        @Test
        @DisplayName("Uma reserva aceita fica gravada no repositório como ativa")
        void gravaNoRepositorio() {
            Reserva reserva = centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(10, 1), 3);

            assertTrue(reserva.estaAtiva());
            assertEquals(List.of(reserva), repositorio.ativasDaSala(101, DIA));
            assertEquals(List.of(reserva), repositorio.ativasDoAluno(ANA, DIA));
        }

        @Test
        @DisplayName("Regra: uma sala não pode ter duas reservas no mesmo horário")
        void recusaConflitoNaMesmaSala() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            central.reservar(ANA, 101, periodo(10, 2), 2);

            assertThrows(RegraVioladaException.class, () -> central.reservar(BETO, 101, periodo(11, 1), 2));
            assertDoesNotThrow(() -> central.reservar(BETO, 102, periodo(11, 1), 2));
        }

        @Test
        @DisplayName("Regra: o tamanho do grupo não pode passar da capacidade da sala (sala 101 tem 4 lugares)")
        void respeitaCapacidade() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);

            assertThrows(RegraVioladaException.class, () -> central.reservar(ANA, 101, periodo(10, 1), 5));
            assertDoesNotThrow(() -> central.reservar(ANA, 101, periodo(10, 1), 4));
        }

        @Test
        @DisplayName("Regra: 1h + 1h no mesmo dia, em salas diferentes, cabe na cota do aluno")
        void cotaUmaMaisUma() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            central.reservar(ANA, 101, periodo(8, 1), 1);

            assertDoesNotThrow(() -> central.reservar(ANA, 102, periodo(14, 1), 1));
            assertEquals(2, repositorio.ativasDoAluno(ANA, DIA).size());
        }

        @Test
        @DisplayName("Regra: 2h + 1h no mesmo dia passa do limite e é recusado, mesmo em outra sala")
        void cotaDuasMaisUma() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            central.reservar(ANA, 101, periodo(8, 2), 1);

            assertThrows(RegraVioladaException.class, () -> central.reservar(ANA, 102, periodo(14, 1), 1));
            assertEquals(1, repositorio.ativasDoAluno(ANA, DIA).size());
        }

        @Test
        @DisplayName("Regra: a cota é por dia; reservas em outro dia não se somam")
        void cotaEPorDia() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            central.reservar(ANA, 101, periodo(8, 2), 1);

            assertDoesNotThrow(() -> central.reservar(ANA, 101, new Periodo(DIA.plusDays(1), 8, 2), 1));
        }

        @ParameterizedTest(name = "[{index}] às 15h, reservar às {0}h")
        @ValueSource(ints = {8, 14, 15})
        @DisplayName("Regra: não se pode reservar no passado (o relógio marca 15h; horários já iniciados são recusados)")
        void recusaHorarioQueJaComecou(int hora) {
            CentralDeReservas central = centralEm(DIA.atTime(15, 0));

            assertThrows(RegraVioladaException.class, () -> central.reservar(ANA, 101, periodo(hora, 1), 1));
            assertTrue(repositorio.ativasDaSala(101, DIA).isEmpty());
        }

        @ParameterizedTest(name = "[{index}] às 15h, reservar às {0}h")
        @ValueSource(ints = {16, 21})
        @DisplayName("Regra: não se pode reservar no passado (o relógio marca 15h; horários futuros do mesmo dia são aceitos)")
        void aceitaHorarioFuturoNoMesmoDia(int hora) {
            CentralDeReservas central = centralEm(DIA.atTime(15, 0));

            assertDoesNotThrow(() -> central.reservar(ANA, 101, periodo(hora, 1), 1));
        }

        @Test
        @DisplayName("Regra: não se pode reservar no passado (dia anterior é recusado)")
        void recusaDiaAnterior() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);

            assertThrows(RegraVioladaException.class,
                    () -> central.reservar(ANA, 101, new Periodo(DIA.minusDays(1), 16, 1), 1));
        }
    }

    @Nested
    @DisplayName("Ao cancelar")
    class AoCancelar {

        private Reserva reservaDaAnaAsQuatorze() {
            return centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(14, 1), 2);
        }

        private StatusReserva statusGravado(Reserva reserva) {
            return repositorio.buscarPorId(reserva.id()).orElseThrow().status();
        }

        @Test
        @DisplayName("Regra: cancelar exatamente 1 hora antes do início é permitido")
        void cancelaExatamenteUmaHoraAntes() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            centralEm(DIA.atTime(13, 0)).cancelar(reserva.id(), ANA);

            assertEquals(StatusReserva.CANCELADA, statusGravado(reserva));
            assertTrue(repositorio.ativasDaSala(101, DIA).isEmpty());
        }

        @Test
        @DisplayName("Regra: um segundo depois do limite de 1 hora antes, o cancelamento é recusado")
        void recusaUmSegundoDepoisDoLimite() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            assertThrows(RegraVioladaException.class,
                    () -> centralEm(DIA.atTime(13, 0, 1)).cancelar(reserva.id(), ANA));
            assertEquals(StatusReserva.ATIVA, statusGravado(reserva));
        }

        @Test
        @DisplayName("Regra acrescentada: só o aluno dono da reserva pode cancelá-la")
        void soODonoCancela() {
            Reserva reserva = reservaDaAnaAsQuatorze();

            assertThrows(RegraVioladaException.class,
                    () -> centralEm(DIA.atTime(9, 0)).cancelar(reserva.id(), BETO));
            assertEquals(StatusReserva.ATIVA, statusGravado(reserva));
        }

        @Test
        @DisplayName("Regra: a reserva cancelada não conta para a cota diária do aluno")
        void canceladaLiberaACota() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            Reserva reserva = central.reservar(ANA, 101, periodo(10, 2), 2);

            central.cancelar(reserva.id(), ANA);

            assertDoesNotThrow(() -> central.reservar(ANA, 102, periodo(14, 2), 2));
        }

        @Test
        @DisplayName("Regra: a reserva cancelada não conta para o conflito de horário da sala")
        void canceladaLiberaOHorario() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            Reserva reserva = central.reservar(ANA, 101, periodo(10, 2), 2);

            central.cancelar(reserva.id(), ANA);

            assertDoesNotThrow(() -> central.reservar(BETO, 101, periodo(10, 2), 2));
        }
    }

    @Nested
    @DisplayName("Ao buscar salas livres")
    class AoBuscar {

        // Salas: 101 (4, sem projetor) | 102 (8, projetor) | 103 (10, só lousa) | 104 (6, projetor + lousa)
        // A 104 está reservada das 10h às 12h em cada caso.
        @ParameterizedTest(name = "[{index}] {0}h por {1}h, mín. {2} pessoas, exige projetor = {3} → salas [{4}]")
        @CsvSource({
            "10, 1, 1, false, '101 102 103'",
            "10, 1, 1, true,  '102'",
            "11, 2, 1, true,  '102'",
            "12, 1, 1, true,  '102 104'",
            "14, 1, 7, false, '102 103'",
            "14, 1, 4, false, '101 102 103 104'",
            "14, 1, 9, true,  ''"})
        @DisplayName("Regra: o aluno busca salas livres num horário, com capacidade mínima e, opcionalmente, projetor")
        void buscaSalasLivres(int hora, int duracao, int capacidadeMinima, boolean exigeProjetor, String esperadas) {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            central.reservar(BETO, 104, periodo(10, 2), 3);

            List<Sala> livres = central.buscarSalasLivres(
                    new CriterioDeBusca(periodo(hora, duracao), capacidadeMinima, exigeProjetor));

            assertEquals(numeros(esperadas), livres.stream().map(Sala::numero).collect(Collectors.toSet()));
        }

        @Test
        @DisplayName("Regra: uma reserva cancelada não impede a sala de aparecer na busca")
        void canceladaNaoOcupaNaBusca() {
            CentralDeReservas central = centralEm(SETE_DA_MANHA);
            Reserva reserva = central.reservar(ANA, 102, periodo(10, 1), 2);
            central.cancelar(reserva.id(), ANA);

            List<Sala> livres = central.buscarSalasLivres(new CriterioDeBusca(periodo(10, 1), 8, true));

            assertEquals(Set.of(102), livres.stream().map(Sala::numero).collect(Collectors.toSet()));
        }
    }
}
```

## 4. Casos de borda que você pediu

| Caso | Onde está |
|---|---|
| Reserva às 8h e às 21h com 1 hora | `PeriodoTest.aceitaPeriodoDentroDoHorario` |
| 21h com 2 horas falha | `PeriodoTest.recusaPeriodoForaDoHorario` |
| Duração 0 e 3 | `PeriodoTest.recusaDuracaoForaDoIntervalo` |
| Grupo igual à capacidade passa, uma pessoa a mais falha | `SalaTest.comporta`, `ReservaTest.Criacao` e `CentralDeReservasTest.respeitaCapacidade` |
| Cota 1h + 1h passa, 2h + 1h falha | `CotaDiariaDoAlunoTest.limiteDiario` (domínio) e `cotaUmaMaisUma` / `cotaDuasMaisUma` (`CentralDeReservas`) |
| Cancelada não conta para cota nem para conflito | testes de domínio, da `CentralDeReservas` e da busca |
| Cancelamento exatamente 1 hora antes passa, um segundo depois falha | `ReservaTest.Cancelamento` e `CentralDeReservasTest.AoCancelar` |
| O ajuste 1 está funcionando | `AgendaDaSalaTest.agendaAplicaACota` e `cotaCompartilhadaAcumula` |

## 5. O que os testes deixam em aberto

Estes pontos não estão especificados. Se quiser travar algum deles antes de partir para o código, me diga:

- reservar numa sala que não existe;
- cancelar uma reserva que não existe;
- validar `Matricula` vazia;
- cadastrar duas salas com o mesmo número;
- se a busca deve esconder horários que já passaram.
