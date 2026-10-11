# Rodada 4 — Resposta da IA: correções depois da revisão

Resposta recebida na íntegra, sem edição. Os arquivos foram extraídos daqui, sem nenhuma alteração.

---

# Quatro mutações sobreviventes: correções e testes novos

As quatro mutações que passaram tinham a mesma causa: os testes conferiam o resultado e nunca a forma como ele foi gravado ou checado. Seguem quatro arquivos de teste novos e três arquivos de produção alterados. Os testes já aprovados não mudaram.

Não rodei nada aqui, porque nesta conversa não uso ferramentas. Cada mutação está ligada ao teste que deve pegá-la; vale reaplicar as quatro para confirmar.

## Resumo

| # | Mutação ou ajuste | O que resolve | Arquivo |
|---|---|---|---|
| 1 | `salvar` removido de `cancelar` | Um repositório espião guarda o status de cada reserva no momento em que ela é salva | `CentralDeReservasGravacaoTest` (novo) |
| 2 | Checagem do construtor da `AgendaDaSala` desligada | Testes de outra sala e de outro dia | `ConsistenciaDasListasTest` (novo) |
| 3 | Checagem de `CotaDiariaDoAluno.registrar` desligada | Testes de outro aluno e de outro dia, passando pelo construtor, que chama `registrar` | `ConsistenciaDasListasTest` (novo) |
| 4 | Filtro `estaAtiva` tirado de `ativasDoAluno` | Teste de contrato reutilizável para qualquer repositório | `ContratoDoRepositorioDeReservas` + `RepositorioDeReservasEmMemoriaTest` (novos) |
| 5 | `ReservaId.toString()` cortava o UUID | Passa a mostrar o UUID completo | `ReservaId` (alterado) |
| 6 | `CatalogoDeSalas.todas()` sobrando | Removido | `CatalogoDeSalas` (alterado) |

Na mesma revisão achei mais duas checagens sem teste, do mesmo tipo das mutações 2 e 3: `AgendaDaSala.estaLivre` e `CotaDiariaDoAluno.garantirQueCabe` com um período de outro dia. Também estão cobertas agora, no `ConsistenciaDasListasTest`.

## Sobre a mutação 1: o `RepositorioDeReservasEmMemoria` deve mudar?

**Recomendo não mudar o comportamento, só documentar.** Para que ele se comportasse como um banco, guardando cópias em vez de referências, o pacote `reservas.infra` precisaria recriar objetos `Reserva`. Isso exigiria um construtor ou uma fábrica pública em `Reserva`. Esse construtor quebraria a garantia mais importante do modelo: hoje, uma reserva só nasce pela `AgendaDaSala`.

Guardar referências é o que um *identity map* faz, então não é um defeito. O problema real era outro: nenhum teste observava o momento em que a reserva é gravada, e o espião resolve isso. Mudei só o Javadoc do repositório, para deixar essa semântica explícita.

O contrato do repositório também ganhou um teste de que salvar depois de cancelar mantém o cancelamento. Assim, uma futura implementação com banco vai precisar honrar isso.

## 1. Testes novos

### `src/test/java/reservas/aplicacao/CentralDeReservasGravacaoTest.java`

```java
package reservas.aplicacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;
import reservas.dominio.StatusReserva;
import reservas.infra.RepositorioDeReservasEmMemoria;

@DisplayName("CentralDeReservas: gravação no repositório")
class CentralDeReservasGravacaoTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    private static final LocalDateTime SETE_DA_MANHA = DIA.atTime(7, 0);
    private static final Matricula ANA = new Matricula("RA001");
    private static final Matricula BETO = new Matricula("RA002");

    /** O que o repositório recebeu numa chamada a salvar: qual reserva e em que status ela estava. */
    private record Gravacao(ReservaId id, StatusReserva status) {
    }

    /**
     * Repositório espião: anota o status de cada reserva no instante em que ela é salva e
     * delega o resto ao repositório em memória. Assim, um cancelamento que não chama
     * salvar fica visível, mesmo que o objeto em memória já esteja cancelado.
     */
    private static final class RepositorioEspiao implements RepositorioDeReservas {

        private final RepositorioDeReservas real = new RepositorioDeReservasEmMemoria();
        private final List<Gravacao> gravacoes = new ArrayList<>();

        @Override
        public void salvar(Reserva reserva) {
            gravacoes.add(new Gravacao(reserva.id(), reserva.status()));
            real.salvar(reserva);
        }

        @Override
        public Optional<Reserva> buscarPorId(ReservaId id) {
            return real.buscarPorId(id);
        }

        @Override
        public List<Reserva> ativasDaSala(int numeroSala, LocalDate data) {
            return real.ativasDaSala(numeroSala, data);
        }

        @Override
        public List<Reserva> ativasDoAluno(Matricula aluno, LocalDate data) {
            return real.ativasDoAluno(aluno, data);
        }
    }

    private final RepositorioEspiao espiao = new RepositorioEspiao();
    private final CatalogoDeSalas catalogo = new CatalogoDeSalas();

    @BeforeEach
    void cadastrarSala() {
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
    }

    private CentralDeReservas centralEm(LocalDateTime agora) {
        Clock relogio = Clock.fixed(agora.atZone(FUSO).toInstant(), FUSO);
        return new CentralDeReservas(relogio, espiao, catalogo);
    }

    private static Periodo periodo(int hora, int duracao) {
        return new Periodo(DIA, hora, duracao);
    }

    @Test
    @DisplayName("Reservar grava a reserva nova, ativa, uma única vez")
    void reservarGravaUmaVez() {
        Reserva reserva = centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(10, 1), 2);

        assertEquals(List.of(new Gravacao(reserva.id(), StatusReserva.ATIVA)), espiao.gravacoes);
    }

    @Test
    @DisplayName("Cancelar grava a reserva já com o status CANCELADA")
    void cancelarGravaOCancelamento() {
        Reserva reserva = centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(10, 1), 2);

        centralEm(SETE_DA_MANHA).cancelar(reserva.id(), ANA);

        assertEquals(List.of(
                new Gravacao(reserva.id(), StatusReserva.ATIVA),
                new Gravacao(reserva.id(), StatusReserva.CANCELADA)), espiao.gravacoes);
    }

    @Test
    @DisplayName("Uma reserva recusada não grava nada")
    void reservaRecusadaNaoGrava() {
        Reserva primeira = centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(10, 1), 2);

        assertThrows(RegraVioladaException.class,
                () -> centralEm(SETE_DA_MANHA).reservar(BETO, 101, periodo(10, 1), 2));

        assertEquals(List.of(new Gravacao(primeira.id(), StatusReserva.ATIVA)), espiao.gravacoes);
    }

    @Test
    @DisplayName("Um cancelamento recusado não grava nada")
    void cancelamentoRecusadoNaoGrava() {
        Reserva reserva = centralEm(SETE_DA_MANHA).reservar(ANA, 101, periodo(10, 1), 2);

        assertThrows(RegraVioladaException.class,
                () -> centralEm(DIA.atTime(9, 30)).cancelar(reserva.id(), ANA));

        assertEquals(List.of(new Gravacao(reserva.id(), StatusReserva.ATIVA)), espiao.gravacoes);
    }
}
```

### `src/test/java/reservas/dominio/ConsistenciaDasListasTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static reservas.dominio.Exemplos.ANA;
import static reservas.dominio.Exemplos.BEM_ANTES;
import static reservas.dominio.Exemplos.BETO;
import static reservas.dominio.Exemplos.DIA;
import static reservas.dominio.Exemplos.agendaVazia;
import static reservas.dominio.Exemplos.cotaVazia;
import static reservas.dominio.Exemplos.reservaDe;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Consistência entre agenda, cota e reservas")
class ConsistenciaDasListasTest {

    private static final LocalDate AMANHA = DIA.plusDays(1);

    private final Sala sala101 = Exemplos.sala(101, 6);
    private final Sala sala102 = Exemplos.sala(102, 6);

    private static Reserva reservaDeAmanha(Matricula aluno, Sala sala) {
        return new AgendaDaSala(sala, AMANHA, List.of()).reservar(
                aluno, new Periodo(AMANHA, 10, 1), 1, new CotaDiariaDoAluno(aluno, AMANHA, List.of()), BEM_ANTES);
    }

    @Nested
    @DisplayName("AgendaDaSala")
    class Agenda {

        @Test
        @DisplayName("Uso indevido: a agenda recusa, na construção, reserva de outra sala")
        void recusaReservaDeOutraSala() {
            Reserva daOutraSala = reservaDe(ANA, sala102, 10, 1);

            assertThrows(IllegalArgumentException.class,
                    () -> new AgendaDaSala(sala101, DIA, List.of(daOutraSala)));
        }

        @Test
        @DisplayName("Uso indevido: a agenda recusa, na construção, reserva de outro dia")
        void recusaReservaDeOutroDia() {
            Reserva deAmanha = reservaDeAmanha(ANA, sala101);

            assertThrows(IllegalArgumentException.class,
                    () -> new AgendaDaSala(sala101, DIA, List.of(deAmanha)));
        }

        @Test
        @DisplayName("Uso indevido: a agenda recusa consultar um período de outro dia")
        void estaLivreRecusaPeriodoDeOutroDia() {
            AgendaDaSala agenda = agendaVazia(sala101);

            assertThrows(IllegalArgumentException.class,
                    () -> agenda.estaLivre(new Periodo(AMANHA, 10, 1)));
        }
    }

    @Nested
    @DisplayName("CotaDiariaDoAluno")
    class Cota {

        @Test
        @DisplayName("Uso indevido: a cota recusa reserva de outro aluno")
        void recusaReservaDeOutroAluno() {
            Reserva doBeto = reservaDe(BETO, sala101, 10, 1);

            assertThrows(IllegalArgumentException.class,
                    () -> new CotaDiariaDoAluno(ANA, DIA, List.of(doBeto)));
        }

        @Test
        @DisplayName("Uso indevido: a cota recusa reserva de outro dia")
        void recusaReservaDeOutroDia() {
            Reserva deAmanha = reservaDeAmanha(ANA, sala101);

            assertThrows(IllegalArgumentException.class,
                    () -> new CotaDiariaDoAluno(ANA, DIA, List.of(deAmanha)));
        }

        @Test
        @DisplayName("Uso indevido: a cota recusa verificar um período de outro dia")
        void garantirQueCabeRecusaPeriodoDeOutroDia() {
            CotaDiariaDoAluno cota = cotaVazia(ANA);

            assertThrows(IllegalArgumentException.class,
                    () -> cota.garantirQueCabe(new Periodo(AMANHA, 10, 1)));
        }
    }
}
```

Em todos esses testes, a reserva é criada **fora** do `assertThrows`. Assim, a exceção só pode vir da checagem que está sendo testada, e não da criação da reserva.

### `src/test/java/reservas/infra/ContratoDoRepositorioDeReservas.java`

```java
package reservas.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reservas.dominio.AgendaDaSala;
import reservas.dominio.CotaDiariaDoAluno;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;
import reservas.dominio.StatusReserva;

/**
 * Contrato que toda implementação de {@link RepositorioDeReservas} deve cumprir.
 * Uma implementação nova só precisa de uma subclasse que diga como criar o repositório.
 */
abstract class ContratoDoRepositorioDeReservas {

    private static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    private static final LocalDate AMANHA = DIA.plusDays(1);
    private static final LocalDateTime ANTES = DIA.minusDays(1).atTime(12, 0);
    private static final Matricula ANA = new Matricula("RA001");
    private static final Matricula BETO = new Matricula("RA002");
    private static final Sala SALA_101 = new Sala(101, 6, new Recursos(false, false));
    private static final Sala SALA_102 = new Sala(102, 6, new Recursos(true, false));

    private RepositorioDeReservas repositorio;

    protected abstract RepositorioDeReservas novoRepositorio();

    @BeforeEach
    void criarRepositorio() {
        repositorio = novoRepositorio();
    }

    private static Reserva reserva(Matricula aluno, Sala sala, LocalDate dia, int hora) {
        return new AgendaDaSala(sala, dia, List.of()).reservar(
                aluno, new Periodo(dia, hora, 1), 1, new CotaDiariaDoAluno(aluno, dia, List.of()), ANTES);
    }

    private Reserva salva(Reserva reserva) {
        repositorio.salvar(reserva);
        return reserva;
    }

    /** Salva, cancela e salva de novo, como faz a CentralDeReservas. */
    private Reserva salvaCancelada(Reserva reserva) {
        repositorio.salvar(reserva);
        reserva.cancelar(reserva.aluno(), ANTES);
        repositorio.salvar(reserva);
        return reserva;
    }

    private static Set<ReservaId> ids(List<Reserva> reservas) {
        return Set.copyOf(reservas.stream().map(Reserva::id).toList());
    }

    @Test
    @DisplayName("Contrato: buscarPorId devolve a reserva salva")
    void buscarPorIdDevolveASalva() {
        Reserva reserva = salva(reserva(ANA, SALA_101, DIA, 10));

        Reserva encontrada = repositorio.buscarPorId(reserva.id()).orElseThrow();

        assertEquals(reserva.id(), encontrada.id());
        assertEquals(StatusReserva.ATIVA, encontrada.status());
    }

    @Test
    @DisplayName("Contrato: buscarPorId devolve vazio para um identificador desconhecido")
    void buscarPorIdDesconhecido() {
        assertEquals(Optional.empty(), repositorio.buscarPorId(new ReservaId(UUID.randomUUID())));
    }

    @Test
    @DisplayName("Contrato: salvar a mesma reserva de novo atualiza em vez de duplicar")
    void salvarDeNovoNaoDuplica() {
        Reserva reserva = salva(reserva(ANA, SALA_101, DIA, 10));
        repositorio.salvar(reserva);

        assertEquals(1, repositorio.ativasDaSala(101, DIA).size());
        assertEquals(1, repositorio.ativasDoAluno(ANA, DIA).size());
    }

    @Test
    @DisplayName("Contrato: salvar depois de cancelar mantém o cancelamento")
    void cancelamentoSalvoFicaGravado() {
        Reserva reserva = salvaCancelada(reserva(ANA, SALA_101, DIA, 10));

        assertEquals(StatusReserva.CANCELADA, repositorio.buscarPorId(reserva.id()).orElseThrow().status());
    }

    @Test
    @DisplayName("Contrato: ativasDaSala não devolve reserva cancelada")
    void ativasDaSalaIgnoraCancelada() {
        salvaCancelada(reserva(ANA, SALA_101, DIA, 10));

        assertTrue(repositorio.ativasDaSala(101, DIA).isEmpty());
    }

    @Test
    @DisplayName("Contrato: ativasDoAluno não devolve reserva cancelada")
    void ativasDoAlunoIgnoraCancelada() {
        salvaCancelada(reserva(ANA, SALA_101, DIA, 10));

        assertTrue(repositorio.ativasDoAluno(ANA, DIA).isEmpty());
    }

    @Test
    @DisplayName("Contrato: ativasDaSala devolve só as reservas ativas daquela sala naquele dia")
    void ativasDaSalaFiltraSalaDiaEStatus() {
        Reserva esperada = salva(reserva(ANA, SALA_101, DIA, 10));
        salva(reserva(BETO, SALA_102, DIA, 10));          // outra sala
        salva(reserva(ANA, SALA_101, AMANHA, 10));        // outro dia
        salvaCancelada(reserva(BETO, SALA_101, DIA, 14)); // cancelada

        assertEquals(Set.of(esperada.id()), ids(repositorio.ativasDaSala(101, DIA)));
    }

    @Test
    @DisplayName("Contrato: ativasDoAluno devolve só as reservas ativas daquele aluno naquele dia")
    void ativasDoAlunoFiltraAlunoDiaEStatus() {
        Reserva manha = salva(reserva(ANA, SALA_101, DIA, 10));
        Reserva tarde = salva(reserva(ANA, SALA_102, DIA, 14));
        salva(reserva(BETO, SALA_101, DIA, 16));          // outro aluno
        salva(reserva(ANA, SALA_101, AMANHA, 10));        // outro dia
        salvaCancelada(reserva(ANA, SALA_101, DIA, 18));  // cancelada

        assertEquals(Set.of(manha.id(), tarde.id()), ids(repositorio.ativasDoAluno(ANA, DIA)));
    }
}
```

### `src/test/java/reservas/infra/RepositorioDeReservasEmMemoriaTest.java`

```java
package reservas.infra;

import org.junit.jupiter.api.DisplayName;
import reservas.dominio.RepositorioDeReservas;

@DisplayName("RepositorioDeReservasEmMemoria")
class RepositorioDeReservasEmMemoriaTest extends ContratoDoRepositorioDeReservas {

    @Override
    protected RepositorioDeReservas novoRepositorio() {
        return new RepositorioDeReservasEmMemoria();
    }
}
```

As duas classes ficam no mesmo pacote, `reservas.infra`. Isso permite que os métodos de teste continuem package-private e evita problemas de herança entre pacotes no JUnit. Quando você criar um repositório com banco, basta escrever outra subclasse de quatro linhas.

## 2. Código de produção alterado

### `src/main/java/reservas/dominio/ReservaId.java`

```java
package reservas.dominio;

import java.util.Objects;
import java.util.UUID;

/** Identificador único de uma reserva. */
public record ReservaId(UUID valor) {

    public ReservaId {
        Objects.requireNonNull(valor, "O identificador da reserva é obrigatório.");
    }

    public static ReservaId nova() {
        return new ReservaId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return valor.toString();
    }
}
```

### `src/main/java/reservas/dominio/CatalogoDeSalas.java`

```java
package reservas.dominio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** Conjunto das salas da faculdade, sem números repetidos. */
public final class CatalogoDeSalas {

    private final Map<Integer, Sala> salas = new LinkedHashMap<>();

    public void cadastrar(Sala sala) {
        Objects.requireNonNull(sala, "A sala é obrigatória.");
        if (salas.containsKey(sala.numero())) {
            throw new RegraVioladaException("Já existe uma sala com o número " + sala.numero() + ".");
        }
        salas.put(sala.numero(), sala);
    }

    public Optional<Sala> buscar(int numero) {
        return Optional.ofNullable(salas.get(numero));
    }

    /** Salas que atendem aos requisitos fixos do critério, sem considerar a disponibilidade. */
    public List<Sala> candidatas(CriterioDeBusca criterio) {
        return salas.values().stream().filter(criterio::aceita).toList();
    }
}
```

### `src/main/java/reservas/infra/RepositorioDeReservasEmMemoria.java`

Aqui mudou só o Javadoc da classe.

```java
package reservas.infra;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import reservas.dominio.Matricula;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;

/**
 * Implementação de {@link RepositorioDeReservas} que guarda as reservas num mapa em memória.
 *
 * <p>Guarda referências, não cópias, como um identity map: uma alteração feita num objeto já
 * salvo aparece aqui mesmo sem nova chamada a {@code salvar}. Quem usa o repositório não pode
 * contar com isso; ele deve sempre chamar {@code salvar} depois de alterar uma reserva, como
 * exigem {@code ContratoDoRepositorioDeReservas} e {@code CentralDeReservasGravacaoTest}.
 */
public final class RepositorioDeReservasEmMemoria implements RepositorioDeReservas {

    private final Map<ReservaId, Reserva> dados = new LinkedHashMap<>();

    @Override
    public void salvar(Reserva reserva) {
        Objects.requireNonNull(reserva, "A reserva é obrigatória.");
        dados.put(reserva.id(), reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(ReservaId id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Reserva> ativasDaSala(int numeroSala, LocalDate data) {
        return dados.values().stream()
                .filter(Reserva::estaAtiva)
                .filter(reserva -> reserva.sala().numero() == numeroSala)
                .filter(reserva -> reserva.periodo().data().equals(data))
                .toList();
    }

    @Override
    public List<Reserva> ativasDoAluno(Matricula aluno, LocalDate data) {
        return dados.values().stream()
                .filter(Reserva::estaAtiva)
                .filter(reserva -> reserva.pertenceA(aluno))
                .filter(reserva -> reserva.periodo().data().equals(data))
                .toList();
    }
}
```

Com o ajuste 5, a primeira linha da saída do `ReservasApp` passa a mostrar o UUID completo, por exemplo `Reserva 3f2a9c1e-7b4d-4c1a-9e2f-0a1b2c3d4e5f: sala 102, ...`. O resto da saída não muda.

## 3. Lacunas que eu ainda vejo

Estes pontos não foram pedidos e ficaram de fora, mas mutações neles também sobreviveriam:

- **Checagens de nulo** (`Objects.requireNonNull`) em todos os construtores: nenhum teste passa `null`, então tirar qualquer uma delas não derruba nada.
- **A forma do teste de horário em `Periodo`**: trocar `horaInicio > FECHAMENTO - duracaoHoras` por `horaInicio + duracaoHoras > FECHAMENTO` passa em todos os testes. Só um caso com `horaInicio = Integer.MAX_VALUE` pegaria o estouro de `int`.
- **Os `toString`** de `Sala`, `Reserva` e `Periodo`: só a saída do app depende deles.

Se quiser automatizar essa caça, o PIT (`pitest-maven`) gera e roda esse tipo de mutação sozinho e lista as que sobreviveram.
