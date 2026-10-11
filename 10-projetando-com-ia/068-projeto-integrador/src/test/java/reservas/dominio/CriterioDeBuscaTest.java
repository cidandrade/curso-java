package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reservas.dominio.Exemplos.periodo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("CriterioDeBusca")
class CriterioDeBuscaTest {

    @ParameterizedTest(name = "[{index}] sala({0} lugares, projetor={1}) x busca(mín. {2}, exige projetor={3}) → {4}")
    @CsvSource({
        "6, false, 6, false, true",
        "6, false, 7, false, false",
        "6, false, 1, true,  false",
        "6, true,  1, true,  true",
        "6, true,  1, false, true",
        "6, true,  7, true,  false"})
    @DisplayName("Regra: a busca exige capacidade mínima e, opcionalmente, projetor")
    void aceita(int capacidade, boolean projetor, int minima, boolean exigeProjetor, boolean esperado) {
        Sala sala = new Sala(101, capacidade, new Recursos(projetor, false));
        CriterioDeBusca criterio = new CriterioDeBusca(periodo(10, 1), minima, exigeProjetor);

        assertEquals(esperado, criterio.aceita(sala));
    }

    @ParameterizedTest(name = "[{index}] capacidade mínima {0}")
    @ValueSource(ints = {0, -1})
    @DisplayName("Regra de apoio: a capacidade mínima da busca é de pelo menos 1 pessoa")
    void recusaCapacidadeMinimaInvalida(int minima) {
        assertThrows(RegraVioladaException.class,
                () -> new CriterioDeBusca(periodo(10, 1), minima, false));
    }
}
