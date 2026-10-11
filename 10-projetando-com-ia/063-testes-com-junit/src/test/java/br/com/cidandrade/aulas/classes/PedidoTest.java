package br.com.cidandrade.aulas.classes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Especificação do Pedido. Cada teste segue o roteiro
 * Arrange (preparar), Act (agir) e Assert (verificar), e o nome
 * de cada um descreve uma regra de negócio
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Pedido")
class PedidoTest {

    private Produto cafe;
    private Produto paoDeQueijo;
    private Pedido pedido;

    @BeforeEach
    void preparar() {
        cafe = new Produto("Café", new BigDecimal("5.00"));
        paoDeQueijo = new Produto("Pão de queijo", new BigDecimal("4.50"));
        pedido = new Pedido();
    }

    @Test
    @DisplayName("começa ABERTO, vazio e com total zero")
    void pedidoNovo() {
        assertAll(
                () -> assertEquals(Situacao.ABERTO, pedido.getSituacao()),
                () -> assertTrue(pedido.getItens().isEmpty()),
                () -> assertEquals(BigDecimal.ZERO, pedido.total()));
    }

    @Test
    @DisplayName("total é a soma dos subtotais")
    void total() {
        pedido.adicionar(cafe, 2);         // 10.00
        pedido.adicionar(paoDeQueijo, 3);  // 13.50

        assertEquals(new BigDecimal("23.50"), pedido.total());
    }

    @Test
    @DisplayName("a lista de itens não pode ser alterada por fora")
    void itensProtegidos() {
        pedido.adicionar(cafe, 1);

        assertThrows(UnsupportedOperationException.class,
                () -> pedido.getItens().clear());
    }

    @Nested
    @DisplayName("ao pagar")
    class AoPagar {

        @Test
        @DisplayName("passa a PAGO")
        void pagar() {
            pedido.adicionar(cafe, 1);

            pedido.pagar();

            assertEquals(Situacao.PAGO, pedido.getSituacao());
        }

        @Test
        @DisplayName("recusa pedido vazio")
        void pedidoVazio() {
            assertThrows(IllegalStateException.class, pedido::pagar);
            assertEquals(Situacao.ABERTO, pedido.getSituacao());
        }

        @Test
        @DisplayName("recusa pedido cancelado")
        void pedidoCancelado() {
            pedido.adicionar(cafe, 1);
            pedido.cancelar();

            assertThrows(IllegalStateException.class, pedido::pagar);
        }

        @Test
        @DisplayName("não aceita mais itens depois de pago")
        void semItensDepoisDePago() {
            pedido.adicionar(cafe, 1);
            pedido.pagar();

            assertThrows(IllegalStateException.class,
                    () -> pedido.adicionar(paoDeQueijo, 1));
        }
    }

    @Nested
    @DisplayName("ao cancelar")
    class AoCancelar {

        @Test
        @DisplayName("passa a CANCELADO")
        void cancelar() {
            pedido.cancelar();

            assertEquals(Situacao.CANCELADO, pedido.getSituacao());
        }

        @Test
        @DisplayName("recusa pedido já pago")
        void pedidoPago() {
            pedido.adicionar(cafe, 1);
            pedido.pagar();

            assertThrows(IllegalStateException.class, pedido::cancelar);
            assertEquals(Situacao.PAGO, pedido.getSituacao());
        }
    }
}
