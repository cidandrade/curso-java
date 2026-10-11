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
