# Rodada 3 — Resposta da IA: testes das lacunas e código de produção

Resposta recebida na íntegra, sem edição. Os arquivos de `src/main/java` e os três testes novos foram extraídos daqui, sem nenhuma alteração. O `pom.xml` proposto não foi usado: a aula usa o pom do curso (veja [PROCESSO.md](../PROCESSO.md)).

---

# Testes dos pontos em aberto e código de produção completo

Seguem os três arquivos de teste novos e todo o código de produção. Os testes já aprovados ficaram intactos. Não compilei nem rodei nada aqui (nesta conversa não uso ferramentas), mas conferi cada arquivo contra os testes, assinatura por assinatura. Se `mvn test` acusar alguma coisa, me mande a saída.

## 1. Testes novos

### `src/test/java/reservas/dominio/MatriculaTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Matricula")
class MatriculaTest {

    @ParameterizedTest(name = "[{index}] valor = \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    @DisplayName("Regra acrescentada: a matrícula não pode ser nula nem em branco")
    void recusaMatriculaNulaOuEmBranco(String valor) {
        assertThrows(RegraVioladaException.class, () -> new Matricula(valor));
    }

    @Test
    @DisplayName("Uma matrícula preenchida é aceita e identifica o aluno pelo valor")
    void aceitaMatriculaPreenchida() {
        assertEquals("RA001", new Matricula("RA001").valor());
        assertEquals(new Matricula("RA001"), new Matricula("RA001"));
    }
}
```

### `src/test/java/reservas/dominio/CatalogoDeSalasTest.java`

```java
package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CatalogoDeSalas")
class CatalogoDeSalasTest {

    private final CatalogoDeSalas catalogo = new CatalogoDeSalas();

    @Test
    @DisplayName("Regra acrescentada: não se cadastram duas salas com o mesmo número")
    void recusaNumeroRepetido() {
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));

        assertThrows(RegraVioladaException.class,
                () -> catalogo.cadastrar(new Sala(101, 10, new Recursos(true, true))));
        assertEquals(4, catalogo.buscar(101).orElseThrow().capacidade(), "a sala original deve ser mantida");
    }

    @Test
    @DisplayName("Salas com números diferentes são cadastradas e encontradas pelo número")
    void cadastraEBusca() {
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));

        assertEquals(102, catalogo.buscar(102).orElseThrow().numero());
        assertTrue(catalogo.buscar(999).isEmpty());
    }
}
```

### `src/test/java/reservas/aplicacao/CentralDeReservasLacunasTest.java`

```java
package reservas.aplicacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;
import reservas.infra.RepositorioDeReservasEmMemoria;

@DisplayName("CentralDeReservas: pontos que estavam em aberto")
class CentralDeReservasLacunasTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    private static final Matricula ANA = new Matricula("RA001");

    private RepositorioDeReservas repositorio;
    private CatalogoDeSalas catalogo;

    @BeforeEach
    void preparar() {
        repositorio = new RepositorioDeReservasEmMemoria();
        catalogo = new CatalogoDeSalas();
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));
    }

    private CentralDeReservas centralEm(LocalDateTime agora) {
        Clock relogio = Clock.fixed(agora.atZone(FUSO).toInstant(), FUSO);
        return new CentralDeReservas(relogio, repositorio, catalogo);
    }

    @Test
    @DisplayName("Regra acrescentada: não se reserva numa sala que não existe")
    void recusaSalaInexistente() {
        CentralDeReservas central = centralEm(DIA.atTime(7, 0));

        assertThrows(RegraVioladaException.class,
                () -> central.reservar(ANA, 999, new Periodo(DIA, 10, 1), 1));
        assertTrue(repositorio.ativasDoAluno(ANA, DIA).isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: não se cancela uma reserva que não existe")
    void recusaCancelarReservaInexistente() {
        CentralDeReservas central = centralEm(DIA.atTime(7, 0));

        assertThrows(RegraVioladaException.class,
                () -> central.cancelar(new ReservaId(UUID.randomUUID()), ANA));
    }

    @ParameterizedTest(name = "[{index}] às 15h, buscar o horário das {0}h")
    @ValueSource(ints = {8, 14, 15})
    @DisplayName("Regra acrescentada: buscar salas num horário que já começou devolve lista vazia")
    void buscaEmHorarioIniciadoDevolveVazio(int hora) {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(new CriterioDeBusca(new Periodo(DIA, hora, 1), 1, false));

        assertTrue(livres.isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: buscar salas num dia que já passou devolve lista vazia")
    void buscaEmDiaAnteriorDevolveVazio() {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(
                new CriterioDeBusca(new Periodo(DIA.minusDays(1), 16, 1), 1, false));

        assertTrue(livres.isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: um horário que ainda não começou continua aparecendo na busca")
    void buscaEmHorarioFuturoContinuaFuncionando() {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(new CriterioDeBusca(new Periodo(DIA, 16, 1), 1, false));

        assertEquals(Set.of(101, 102), livres.stream().map(Sala::numero).collect(Collectors.toSet()));
    }
}
```

O `CatalogoDeSalasTest` fixa uma API nova: `CatalogoDeSalas.buscar(int)` devolve `Optional<Sala>`.

## 2. Build

### `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>br.edu.faculdade</groupId>
    <artifactId>reservas-salas</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.release>17</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>
        </plugins>
    </build>
</project>
```

## 3. Código de produção

### `src/main/java/reservas/dominio/RegraVioladaException.java`

```java
package reservas.dominio;

/** Sinaliza que uma regra de negócio foi violada; a mensagem é legível para o aluno. */
public final class RegraVioladaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RegraVioladaException(String mensagem) {
        super(mensagem);
    }
}
```

### `src/main/java/reservas/dominio/Periodo.java`

```java
package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Intervalo de horas cheias em um dia, dentro do horário de funcionamento (8h às 22h),
 * com duração de 1 a 2 horas. O fim é exclusivo.
 */
public record Periodo(LocalDate data, int horaInicio, int duracaoHoras) {

    public static final int ABERTURA = 8;
    public static final int FECHAMENTO = 22;
    public static final int DURACAO_MINIMA = 1;
    public static final int DURACAO_MAXIMA = 2;

    public Periodo {
        Objects.requireNonNull(data, "A data do período é obrigatória.");
        if (duracaoHoras < DURACAO_MINIMA || duracaoHoras > DURACAO_MAXIMA) {
            throw new RegraVioladaException(String.format(
                    "A reserva deve durar de %d a %d horas; foi pedido %dh.",
                    DURACAO_MINIMA, DURACAO_MAXIMA, duracaoHoras));
        }
        if (horaInicio < ABERTURA || horaInicio > FECHAMENTO - duracaoHoras) {
            throw new RegraVioladaException(String.format(
                    "As salas funcionam das %dh às %dh; o período %dh–%dh está fora desse horário.",
                    ABERTURA, FECHAMENTO, horaInicio, horaInicio + duracaoHoras));
        }
    }

    public LocalDateTime inicio() {
        return data.atTime(horaInicio, 0);
    }

    public LocalDateTime fim() {
        return inicio().plusHours(duracaoHoras);
    }

    public int horas() {
        return duracaoHoras;
    }

    public boolean sobrepoe(Periodo outro) {
        return inicio().isBefore(outro.fim()) && outro.inicio().isBefore(fim());
    }

    public boolean jaComecouEm(LocalDateTime agora) {
        return !agora.isBefore(inicio());
    }

    @Override
    public String toString() {
        return String.format("%s, %dh–%dh", data, horaInicio, horaInicio + duracaoHoras);
    }
}
```

O teste de horário usa `horaInicio > FECHAMENTO - duracaoHoras` em vez de `horaInicio + duracaoHoras > FECHAMENTO`. As duas formas dizem a mesma coisa, mas a primeira evita estouro de `int` com valores absurdos. A duração é validada antes, então ela já está entre 1 e 2 quando esse teste roda.

### `src/main/java/reservas/dominio/Matricula.java`

```java
package reservas.dominio;

/** Identifica o aluno; não pode ser nula nem em branco. */
public record Matricula(String valor) {

    public Matricula {
        if (valor == null || valor.isBlank()) {
            throw new RegraVioladaException("A matrícula do aluno é obrigatória.");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
```

### `src/main/java/reservas/dominio/Recursos.java`

```java
package reservas.dominio;

/** Equipamentos disponíveis numa sala. */
public record Recursos(boolean projetor, boolean lousaDigital) {
}
```

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
        return valor.toString().substring(0, 8);
    }
}
```

### `src/main/java/reservas/dominio/CriterioDeBusca.java`

```java
package reservas.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

/** O que o aluno procura: um período, uma capacidade mínima e, opcionalmente, projetor. */
public record CriterioDeBusca(Periodo periodo, int capacidadeMinima, boolean exigeProjetor) {

    public CriterioDeBusca {
        Objects.requireNonNull(periodo, "O período da busca é obrigatório.");
        if (capacidadeMinima < 1) {
            throw new RegraVioladaException("A capacidade mínima da busca deve ser de pelo menos 1 pessoa.");
        }
    }

    /** Diz se a sala tem os requisitos fixos da busca (lugares e projetor), sem olhar a agenda. */
    public boolean aceita(Sala sala) {
        return sala.comporta(capacidadeMinima) && (!exigeProjetor || sala.recursos().projetor());
    }

    /** Uma busca só faz sentido para um período que ainda não começou. */
    public boolean podeSerAtendidoEm(LocalDateTime agora) {
        return !periodo.jaComecouEm(agora);
    }
}
```

### `src/main/java/reservas/dominio/Sala.java`

```java
package reservas.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Sala de estudo: conhece número, capacidade e recursos, e diz se um grupo cabe nela. */
public final class Sala {

    private final int numero;
    private final int capacidade;
    private final Recursos recursos;

    public Sala(int numero, int capacidade, Recursos recursos) {
        if (capacidade < 1) {
            throw new RegraVioladaException(String.format(
                    "A capacidade da sala %d deve ser positiva; foi informado %d.", numero, capacidade));
        }
        this.numero = numero;
        this.capacidade = capacidade;
        this.recursos = Objects.requireNonNull(recursos, "Os recursos da sala são obrigatórios.");
    }

    public int numero() {
        return numero;
    }

    public int capacidade() {
        return capacidade;
    }

    public Recursos recursos() {
        return recursos;
    }

    public boolean comporta(int tamanhoGrupo) {
        return tamanhoGrupo <= capacidade;
    }

    @Override
    public boolean equals(Object outro) {
        return outro instanceof Sala sala && sala.numero == numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }

    @Override
    public String toString() {
        List<String> itens = new ArrayList<>();
        itens.add(capacidade + " lugares");
        if (recursos.projetor()) {
            itens.add("projetor");
        }
        if (recursos.lousaDigital()) {
            itens.add("lousa digital");
        }
        return "Sala " + numero + " (" + String.join(", ", itens) + ")";
    }
}
```

### `src/main/java/reservas/dominio/StatusReserva.java`

```java
package reservas.dominio;

/** Situação de uma reserva. */
public enum StatusReserva {
    ATIVA,
    CANCELADA
}
```

### `src/main/java/reservas/dominio/Reserva.java`

```java
package reservas.dominio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Reserva de uma sala por um aluno. Na criação, garante que o grupo cabe na sala e que o
 * horário ainda não começou; depois, sabe se cancelar. Só é criada pela {@link AgendaDaSala}.
 */
public final class Reserva {

    private static final Duration ANTECEDENCIA_PARA_CANCELAR = Duration.ofHours(1);

    private final ReservaId id;
    private final Sala sala;
    private final Matricula aluno;
    private final Periodo periodo;
    private final int tamanhoGrupo;
    private StatusReserva status;

    Reserva(Sala sala, Matricula aluno, Periodo periodo, int tamanhoGrupo, LocalDateTime agora) {
        this.sala = Objects.requireNonNull(sala, "A sala é obrigatória.");
        this.aluno = Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        this.periodo = Objects.requireNonNull(periodo, "O período é obrigatório.");
        Objects.requireNonNull(agora, "O instante atual é obrigatório.");

        if (tamanhoGrupo < 1) {
            throw new RegraVioladaException("O grupo precisa ter pelo menos uma pessoa.");
        }
        if (!sala.comporta(tamanhoGrupo)) {
            throw new RegraVioladaException(String.format(
                    "A sala %d comporta no máximo %d pessoas; o grupo tem %d.",
                    sala.numero(), sala.capacidade(), tamanhoGrupo));
        }
        if (periodo.jaComecouEm(agora)) {
            throw new RegraVioladaException("Não é possível reservar um horário que já começou (" + periodo + ").");
        }

        this.id = ReservaId.nova();
        this.tamanhoGrupo = tamanhoGrupo;
        this.status = StatusReserva.ATIVA;
    }

    public ReservaId id() {
        return id;
    }

    public Sala sala() {
        return sala;
    }

    public Matricula aluno() {
        return aluno;
    }

    public Periodo periodo() {
        return periodo;
    }

    public int tamanhoGrupo() {
        return tamanhoGrupo;
    }

    public StatusReserva status() {
        return status;
    }

    public boolean estaAtiva() {
        return status == StatusReserva.ATIVA;
    }

    public boolean pertenceA(Matricula matricula) {
        return aluno.equals(matricula);
    }

    /** Uma reserva ocupa o período se estiver ativa e se os horários se sobrepuserem. */
    public boolean ocupa(Periodo outro) {
        return estaAtiva() && periodo.sobrepoe(outro);
    }

    /** Horas que a reserva consome da cota do aluno; uma reserva cancelada não consome nada. */
    public int horas() {
        return estaAtiva() ? periodo.horas() : 0;
    }

    /** Cancela a reserva se o solicitante for o dono e se faltar pelo menos 1 hora para o início. */
    public void cancelar(Matricula solicitante, LocalDateTime agora) {
        if (!pertenceA(solicitante)) {
            throw new RegraVioladaException("Só o aluno que fez a reserva pode cancelá-la.");
        }
        if (!estaAtiva()) {
            throw new RegraVioladaException("Esta reserva já foi cancelada.");
        }
        LocalDateTime limite = periodo.inicio().minus(ANTECEDENCIA_PARA_CANCELAR);
        if (agora.isAfter(limite)) {
            throw new RegraVioladaException(
                    "O cancelamento só é permitido até " + limite.toLocalTime() + " (1 hora antes do início).");
        }
        status = StatusReserva.CANCELADA;
    }

    @Override
    public boolean equals(Object outro) {
        return outro instanceof Reserva reserva && reserva.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Reserva %s: sala %d, aluno %s, %s, %d pessoa(s), %s",
                id, sala.numero(), aluno, periodo, tamanhoGrupo, status);
    }
}
```

### `src/main/java/reservas/dominio/CotaDiariaDoAluno.java`

```java
package reservas.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Horas que um aluno já reservou num dia; recusa o que passaria do limite diário de 2 horas. */
public final class CotaDiariaDoAluno {

    public static final int LIMITE_HORAS = 2;

    private final Matricula aluno;
    private final LocalDate data;
    private final List<Reserva> reservas;

    public CotaDiariaDoAluno(Matricula aluno, LocalDate data, List<Reserva> reservas) {
        this.aluno = Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        this.data = Objects.requireNonNull(data, "A data é obrigatória.");
        this.reservas = new ArrayList<>();
        Objects.requireNonNull(reservas, "A lista de reservas é obrigatória.").forEach(this::registrar);
    }

    public Matricula aluno() {
        return aluno;
    }

    public LocalDate data() {
        return data;
    }

    public int horasUsadas() {
        return reservas.stream().mapToInt(Reserva::horas).sum();
    }

    public void garantirQueCabe(Periodo periodo) {
        if (!periodo.data().equals(data)) {
            throw new IllegalArgumentException("O período " + periodo + " não é do dia desta cota (" + data + ").");
        }
        if (horasUsadas() + periodo.horas() > LIMITE_HORAS) {
            throw new RegraVioladaException(String.format(
                    "O aluno %s já tem %dh reservadas em %s; o limite é de %dh por dia.",
                    aluno, horasUsadas(), data, LIMITE_HORAS));
        }
    }

    /** Usado pela agenda logo depois de criar uma reserva, para que a mesma cota acumule as horas. */
    void registrar(Reserva reserva) {
        if (!reserva.pertenceA(aluno) || !reserva.periodo().data().equals(data)) {
            throw new IllegalArgumentException("A reserva " + reserva + " não é do aluno e do dia desta cota.");
        }
        reservas.add(reserva);
    }
}
```

### `src/main/java/reservas/dominio/AgendaDaSala.java`

```java
package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Reservas de uma sala num dia. Garante que não haja dois horários sobrepostos e é o único
 * caminho para criar uma {@link Reserva}, sempre passando pela cota diária do aluno.
 */
public final class AgendaDaSala {

    private final Sala sala;
    private final LocalDate data;
    private final List<Reserva> reservas;

    public AgendaDaSala(Sala sala, LocalDate data, List<Reserva> reservas) {
        this.sala = Objects.requireNonNull(sala, "A sala é obrigatória.");
        this.data = Objects.requireNonNull(data, "A data é obrigatória.");
        this.reservas = new ArrayList<>(Objects.requireNonNull(reservas, "A lista de reservas é obrigatória."));
        for (Reserva reserva : this.reservas) {
            if (!reserva.sala().equals(sala) || !reserva.periodo().data().equals(data)) {
                throw new IllegalArgumentException("A reserva " + reserva + " não pertence a esta agenda.");
            }
        }
    }

    public boolean estaLivre(Periodo periodo) {
        exigirMesmoDia(periodo);
        return reservas.stream().noneMatch(reserva -> reserva.ocupa(periodo));
    }

    public Reserva reservar(Matricula aluno, Periodo periodo, int tamanhoGrupo,
                            CotaDiariaDoAluno cota, LocalDateTime agora) {
        Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        Objects.requireNonNull(cota, "A cota do aluno é obrigatória.");
        Objects.requireNonNull(agora, "O instante atual é obrigatório.");
        exigirMesmoDia(periodo);
        if (!cota.aluno().equals(aluno) || !cota.data().equals(data)) {
            throw new IllegalArgumentException("A cota informada não é do aluno e do dia desta reserva.");
        }

        cota.garantirQueCabe(periodo);
        if (!estaLivre(periodo)) {
            throw new RegraVioladaException(String.format(
                    "A sala %d já está reservada em parte do horário %s.", sala.numero(), periodo));
        }
        Reserva nova = new Reserva(sala, aluno, periodo, tamanhoGrupo, agora);

        reservas.add(nova);
        cota.registrar(nova);
        return nova;
    }

    private void exigirMesmoDia(Periodo periodo) {
        Objects.requireNonNull(periodo, "O período é obrigatório.");
        if (!periodo.data().equals(data)) {
            throw new IllegalArgumentException("O período " + periodo + " não é do dia desta agenda (" + data + ").");
        }
    }
}
```

### `src/main/java/reservas/dominio/RepositorioDeReservas.java`

```java
package reservas.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Armazenamento das reservas; o domínio não sabe se é memória, arquivo ou banco. */
public interface RepositorioDeReservas {

    void salvar(Reserva reserva);

    Optional<Reserva> buscarPorId(ReservaId id);

    List<Reserva> ativasDaSala(int numeroSala, LocalDate data);

    List<Reserva> ativasDoAluno(Matricula aluno, LocalDate data);
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

    public List<Sala> todas() {
        return List.copyOf(salas.values());
    }

    /** Salas que atendem aos requisitos fixos do critério, sem considerar a disponibilidade. */
    public List<Sala> candidatas(CriterioDeBusca criterio) {
        return salas.values().stream().filter(criterio::aceita).toList();
    }
}
```

### `src/main/java/reservas/infra/RepositorioDeReservasEmMemoria.java`

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

/** Implementação de {@link RepositorioDeReservas} que guarda as reservas num mapa em memória. */
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

### `src/main/java/reservas/aplicacao/CentralDeReservas.java`

```java
package reservas.aplicacao;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import reservas.dominio.AgendaDaSala;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CotaDiariaDoAluno;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;

/**
 * Ponto de entrada dos casos de uso. Obtém o "agora" do relógio, carrega e salva dados e
 * delega todas as decisões de negócio aos objetos de domínio.
 */
public final class CentralDeReservas {

    private final Clock relogio;
    private final RepositorioDeReservas repositorio;
    private final CatalogoDeSalas catalogo;

    public CentralDeReservas(Clock relogio, RepositorioDeReservas repositorio, CatalogoDeSalas catalogo) {
        this.relogio = Objects.requireNonNull(relogio, "O relógio é obrigatório.");
        this.repositorio = Objects.requireNonNull(repositorio, "O repositório é obrigatório.");
        this.catalogo = Objects.requireNonNull(catalogo, "O catálogo é obrigatório.");
    }

    public Reserva reservar(Matricula aluno, int numeroSala, Periodo periodo, int tamanhoGrupo) {
        Sala sala = catalogo.buscar(numeroSala)
                .orElseThrow(() -> new RegraVioladaException("Não existe sala com o número " + numeroSala + "."));
        LocalDate dia = periodo.data();
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(aluno, dia, repositorio.ativasDoAluno(aluno, dia));

        Reserva reserva = agendaDe(sala, dia).reservar(aluno, periodo, tamanhoGrupo, cota, agora());

        repositorio.salvar(reserva);
        return reserva;
    }

    public void cancelar(ReservaId id, Matricula solicitante) {
        Reserva reserva = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RegraVioladaException("Reserva não encontrada: " + id + "."));

        reserva.cancelar(solicitante, agora());

        repositorio.salvar(reserva);
    }

    public List<Sala> buscarSalasLivres(CriterioDeBusca criterio) {
        if (!criterio.podeSerAtendidoEm(agora())) {
            return List.of();
        }
        Periodo periodo = criterio.periodo();
        return catalogo.candidatas(criterio).stream()
                .filter(sala -> agendaDe(sala, periodo.data()).estaLivre(periodo))
                .toList();
    }

    private AgendaDaSala agendaDe(Sala sala, LocalDate dia) {
        return new AgendaDaSala(sala, dia, repositorio.ativasDaSala(sala.numero(), dia));
    }

    private LocalDateTime agora() {
        return LocalDateTime.now(relogio);
    }
}
```

### `src/main/java/reservas/ReservasApp.java`

```java
package reservas;

import java.time.Clock;
import java.time.LocalDate;
import reservas.aplicacao.CentralDeReservas;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.Sala;
import reservas.infra.RepositorioDeReservasEmMemoria;

/** Composition root: monta as dependências e mostra um cenário curto no console. */
public final class ReservasApp {

    private ReservasApp() {
    }

    public static void main(String[] args) {
        Clock relogio = Clock.systemDefaultZone();

        CatalogoDeSalas catalogo = new CatalogoDeSalas();
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));
        catalogo.cadastrar(new Sala(103, 10, new Recursos(false, true)));
        catalogo.cadastrar(new Sala(104, 6, new Recursos(true, true)));

        RepositorioDeReservas repositorio = new RepositorioDeReservasEmMemoria();
        CentralDeReservas central = new CentralDeReservas(relogio, repositorio, catalogo);

        // Amanhã inteiro está no futuro, seja qual for a hora em que o programa rodar.
        LocalDate amanha = LocalDate.now(relogio).plusDays(1);
        Matricula ana = new Matricula("RA001");

        System.out.println("== Reservas de salas para " + amanha + " ==");

        Reserva reserva = central.reservar(ana, 102, new Periodo(amanha, 10, 2), 5);
        System.out.println("1. Reserva feita: " + reserva);

        System.out.println("2. Ana tenta reservar mais 1h na sala 101, às 14h:");
        try {
            central.reservar(ana, 101, new Periodo(amanha, 14, 1), 2);
            System.out.println("   Reserva aceita (não deveria acontecer).");
        } catch (RegraVioladaException e) {
            System.out.println("   Recusada: " + e.getMessage());
        }

        System.out.println("3. Salas livres amanhã às 10h, para 4 pessoas, com projetor:");
        central.buscarSalasLivres(new CriterioDeBusca(new Periodo(amanha, 10, 1), 4, true))
                .forEach(sala -> System.out.println("   " + sala));
    }
}
```

A saída esperada é esta (o identificador e a data mudam a cada execução):

```
== Reservas de salas para 2026-10-12 ==
1. Reserva feita: Reserva 3f2a9c1e: sala 102, aluno RA001, 2026-10-12, 10h–12h, 5 pessoa(s), ATIVA
2. Ana tenta reservar mais 1h na sala 101, às 14h:
   Recusada: O aluno RA001 já tem 2h reservadas em 2026-10-12; o limite é de 2h por dia.
3. Salas livres amanhã às 10h, para 4 pessoas, com projetor:
   Sala 104 (6 lugares, projetor, lousa digital)
```

A sala 102 também tem projetor, mas não aparece no passo 3 porque está ocupada pela reserva do passo 1. O cenário não depende da hora em que roda: a data "amanhã" é calculada uma vez só, e mesmo que o relógio passe da meia-noite durante a execução, as 10h desse dia ainda estão no futuro.

Para rodar:

```
mvn test
mvn -q compile && java -cp target/classes reservas.ReservasApp
```

## 4. Três escolhas que você deve conhecer

1. **A regra nova da busca ficou no domínio.** Ela está em `CriterioDeBusca.podeSerAtendidoEm(agora)`, que delega para `Periodo.jaComecouEm`. A `CentralDeReservas` tem um único `if`, que só encaminha essa resposta para uma lista vazia. Quem decide é o domínio.
2. **A agenda e a cota recusam listas inconsistentes.** Uma reserva de outra sala, outro aluno ou outro dia gera `IllegalArgumentException`. Isso pega bugs de um repositório futuro logo na origem. Uma lista *incompleta*, porém, não tem como ser detectada.
3. **Não há setters.** O único estado que muda é o `status` da `Reserva`, e só pelo método `cancelar`. A outra mutação é o registro interno de reservas na agenda e na cota, por métodos package-private.
