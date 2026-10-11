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
