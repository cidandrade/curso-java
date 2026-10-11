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
