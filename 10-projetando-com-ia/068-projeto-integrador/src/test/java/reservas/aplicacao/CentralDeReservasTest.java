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
