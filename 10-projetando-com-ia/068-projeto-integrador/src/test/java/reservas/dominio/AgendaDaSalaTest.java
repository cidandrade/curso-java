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
