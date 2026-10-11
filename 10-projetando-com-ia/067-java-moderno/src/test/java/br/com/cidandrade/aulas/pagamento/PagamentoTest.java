package br.com.cidandrade.aulas.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Interface selada com records e pattern matching
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Pagamentos")
class PagamentoTest {

    private static final BigDecimal TOTAL = new BigDecimal("50.00");

    static Stream<Arguments> taxas() {
        return Stream.of(
                Arguments.of(new Cartao("Visa", 1), "1.00"),
                Arguments.of(new Cartao("Visa", 3), "2.00"),
                Arguments.of(new Pix("cafe@exemplo.com"), "0"),
                Arguments.of(new Dinheiro(new BigDecimal("100.00")), "0"));
    }

    @ParameterizedTest(name = "{0}: taxa de R$ {1}")
    @MethodSource("taxas")
    @DisplayName("cada forma de pagamento calcula a própria taxa")
    void taxa(Pagamento pagamento, String esperada) {
        assertEquals(0, new BigDecimal(esperada).compareTo(pagamento.taxa(TOTAL)));
    }

    static Stream<Arguments> descricoes() {
        return Stream.of(
                Arguments.of(new Cartao("Elo", 1), "Cartão Elo à vista"),
                Arguments.of(new Cartao("Elo", 6), "Cartão Elo em 6x"),
                Arguments.of(new Pix("123"), "Pix (123)"),
                Arguments.of(new Dinheiro(new BigDecimal("20")), "Dinheiro (recebido R$ 20)"));
    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("descricoes")
    @DisplayName("o pattern matching descreve cada tipo")
    void descrever(Pagamento pagamento, String esperada) {
        assertEquals(esperada, Pagamentos.descrever(pagamento));
    }

    @ParameterizedTest(name = "{0} parcelas")
    @ValueSource(ints = {0, 13, -1})
    @DisplayName("o cartão aceita de 1 a 12 parcelas")
    void parcelasInvalidas(int parcelas) {
        assertThrows(IllegalArgumentException.class, () -> new Cartao("Visa", parcelas));
    }

    @Test
    @DisplayName("dinheiro calcula o troco e recusa valor insuficiente")
    void troco() {
        Dinheiro nota = new Dinheiro(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("50.00"), nota.troco(TOTAL));
        assertThrows(IllegalArgumentException.class,
                () -> new Dinheiro(new BigDecimal("20.00")).troco(TOTAL));
    }
}
