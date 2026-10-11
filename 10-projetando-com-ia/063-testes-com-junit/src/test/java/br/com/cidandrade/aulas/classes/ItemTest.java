package br.com.cidandrade.aulas.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Especificação de Produto e Item. Os testes parametrizados verificam
 * a mesma regra com vários exemplos de entrada e saída
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Produto e Item")
class ItemTest {

    @ParameterizedTest(name = "{0} × R$ {1} = R$ {2}")
    @CsvSource({
        "1, 5.00, 5.00",
        "2, 5.00, 10.00",
        "3, 4.50, 13.50",
        "10, 0.10, 1.00"
    })
    @DisplayName("subtotal é preço × quantidade, sem erro de arredondamento")
    void subtotal(int quantidade, String preco, String esperado) {
        Item item = new Item(new Produto("Café", new BigDecimal(preco)), quantidade);

        assertEquals(new BigDecimal(esperado), item.subtotal());
    }

    @ParameterizedTest(name = "quantidade {0}")
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("quantidade deve ser maior que zero")
    void quantidadeInvalida(int quantidade) {
        Produto cafe = new Produto("Café", new BigDecimal("5.00"));

        assertThrows(IllegalArgumentException.class, () -> new Item(cafe, quantidade));
    }

    @ParameterizedTest(name = "preço {0}")
    @ValueSource(strings = {"0", "0.00", "-5.00"})
    @DisplayName("preço deve ser maior que zero")
    void precoInvalido(String preco) {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Café", new BigDecimal(preco)));
    }

    @Test
    @DisplayName("nome em branco é recusado")
    void nomeEmBranco() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("   ", new BigDecimal("5.00")));
    }
}
