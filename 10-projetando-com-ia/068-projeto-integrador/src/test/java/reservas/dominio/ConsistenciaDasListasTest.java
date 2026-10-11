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
