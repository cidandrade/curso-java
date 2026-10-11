package reservas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CatalogoDeSalas")
class CatalogoDeSalasTest {

    private final CatalogoDeSalas catalogo = new CatalogoDeSalas();

    @Test
    @DisplayName("Regra acrescentada: não se cadastram duas salas com o mesmo número")
    void recusaNumeroRepetido() {
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));

        assertThrows(RegraVioladaException.class,
                () -> catalogo.cadastrar(new Sala(101, 10, new Recursos(true, true))));
        assertEquals(4, catalogo.buscar(101).orElseThrow().capacidade(), "a sala original deve ser mantida");
    }

    @Test
    @DisplayName("Salas com números diferentes são cadastradas e encontradas pelo número")
    void cadastraEBusca() {
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));

        assertEquals(102, catalogo.buscar(102).orElseThrow().numero());
        assertTrue(catalogo.buscar(999).isEmpty());
    }
}
