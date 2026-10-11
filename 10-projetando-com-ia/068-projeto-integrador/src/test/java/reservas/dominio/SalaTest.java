package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Sala")
class SalaTest {

    @Test
    @DisplayName("Regra: a sala tem número, capacidade e pode ter projetor e/ou lousa digital")
    void guardaSeusDados() {
        Sala sala = new Sala(101, 6, new Recursos(true, false));

        assertEquals(101, sala.numero());
        assertEquals(6, sala.capacidade());
        assertTrue(sala.recursos().projetor());
        assertFalse(sala.recursos().lousaDigital());
    }

    @ParameterizedTest(name = "[{index}] capacidade {0}")
    @ValueSource(ints = {0, -1})
    @DisplayName("Regra de apoio: a capacidade da sala é positiva")
    void recusaCapacidadeNaoPositiva(int capacidade) {
        assertThrows(RegraVioladaException.class,
                () -> new Sala(101, capacidade, new Recursos(false, false)));
    }

    @ParameterizedTest(name = "[{index}] grupo de {0} numa sala de 6: comporta = {1}")
    @CsvSource({"1, true", "5, true", "6, true", "7, false"})
    @DisplayName("Regra: o tamanho do grupo não pode passar da capacidade da sala")
    void comporta(int grupo, boolean esperado) {
        Sala sala = new Sala(101, 6, new Recursos(false, false));

        assertEquals(esperado, sala.comporta(grupo));
    }

    @Test
    @DisplayName("Regra de apoio: salas com o mesmo número são a mesma sala")
    void identidadePeloNumero() {
        assertEquals(new Sala(101, 6, new Recursos(false, false)),
                     new Sala(101, 10, new Recursos(true, true)));
    }
}
