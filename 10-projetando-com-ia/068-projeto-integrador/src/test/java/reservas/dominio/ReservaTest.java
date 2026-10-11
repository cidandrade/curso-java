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
