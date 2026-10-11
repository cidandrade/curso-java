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
