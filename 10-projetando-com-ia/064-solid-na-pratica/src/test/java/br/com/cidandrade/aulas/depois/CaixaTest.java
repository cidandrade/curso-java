package br.com.cidandrade.aulas.depois;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Testes do pacote depois. Compare: o CaixaCafeteria do pacote antes
 * não tem como ser testado assim, porque imprime e envia e-mail por
 * conta própria
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Caixa (depois do SOLID)")
class CaixaTest {

    private final List<String> notificacoes = new ArrayList<>();
    private Caixa caixa;
    private Pedido pedido;

    @BeforeEach
    void preparar() {
        // [D] No lugar do e-mail de verdade, um notificador falso que
        // só anota as mensagens
        caixa = new Caixa(notificacoes::add);
        pedido = new Pedido();
        pedido.adicionar("Café", new BigDecimal("5.00"), 2);
        pedido.adicionar("Pão de queijo", new BigDecimal("4.50"), 3);
    }

    @Test
    @DisplayName("[S] o recibo lista os itens e o total, sem precisar do caixa")
    void recibo() {
        String recibo = new GeradorRecibo().gerar(pedido);

        assertTrue(recibo.contains("2 × Café: R$ 10.00"));
        assertTrue(recibo.contains("3 × Pão de queijo: R$ 13.50"));
        assertTrue(recibo.endsWith("Total: R$ 23.50"));
    }

    @Test
    @DisplayName("[D] fechar o pedido notifica o cliente uma vez, com o comprovante")
    void notifica() {
        caixa.fechar(pedido, new Pix());

        assertEquals(List.of("Pix: recebidos R$ 23.50"), notificacoes);
    }

    @Test
    @DisplayName("[O] uma forma de pagamento nova funciona sem alterar o Caixa")
    void formaNova() {
        FormaPagamento cripto = valor -> "Cripto: recebidos R$ " + valor;

        String recibo = caixa.fechar(pedido, cripto);

        assertTrue(recibo.endsWith("Cripto: recebidos R$ 23.50"));
    }

    static Stream<Named<FormaPagamento>> todasAsFormas() {
        return Stream.of(Named.of("Cartão", new Cartao()),
                Named.of("Pix", new Pix()),
                Named.of("Vale", new Vale()));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("todasAsFormas")
    @DisplayName("[L] toda FormaPagamento cobra o total sem lançar exceção")
    void substituicao(FormaPagamento forma) {
        String recibo = caixa.fechar(pedido, forma);

        assertTrue(recibo.endsWith("R$ 23.50"));
        assertEquals(1, notificacoes.size());
    }

    @Test
    @DisplayName("[I] o estorno só aceita formas estornáveis")
    void estorno() {
        // caixa.estornar(pedido, new Vale()) nem compila: o erro aparece
        // na hora de escrever o código, e não na mão do cliente
        assertEquals("Cartão: estornados R$ 23.50", caixa.estornar(pedido, new Cartao()));
    }
}
