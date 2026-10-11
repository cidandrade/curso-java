package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Matricula")
class MatriculaTest {

    @ParameterizedTest(name = "[{index}] valor = \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    @DisplayName("Regra acrescentada: a matrícula não pode ser nula nem em branco")
    void recusaMatriculaNulaOuEmBranco(String valor) {
        assertThrows(RegraVioladaException.class, () -> new Matricula(valor));
    }

    @Test
    @DisplayName("Uma matrícula preenchida é aceita e identifica o aluno pelo valor")
    void aceitaMatriculaPreenchida() {
        assertEquals("RA001", new Matricula("RA001").valor());
        assertEquals(new Matricula("RA001"), new Matricula("RA001"));
    }
}
