package br.com.cidandrade.aulas.cardapio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Records, enum com atributo, switch como expressão e text block
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Cardápio e comanda")
class CardapioTest {

    private final Produto cafe = new Produto("Café", new BigDecimal("5.00"));

    @Test
    @DisplayName("records com os mesmos componentes são iguais (equals e hashCode gerados)")
    void igualdadeDeRecords() {
        Produto outro = new Produto("Café", new BigDecimal("5.00"));

        assertEquals(cafe, outro);
        assertEquals(cafe.hashCode(), outro.hashCode());
        assertEquals("Produto[nome=Café, preco=5.00]", cafe.toString());
    }

    @Test
    @DisplayName("a comanda é uma classe: duas comandas iguais por fora não são a mesma")
    void comandaTemIdentidade() {
        assertNotEquals(new Comanda(1), new Comanda(1));
    }

    @Test
    @DisplayName("o construtor compacto valida os componentes")
    void construtorCompacto() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(" ", BigDecimal.TEN));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new ItemPedido(cafe, Tamanho.MEDIO, 0));
    }

    @Test
    @DisplayName("subtotal = (preço + acréscimo do tamanho) × quantidade")
    void subtotal() {
        assertEquals(new BigDecimal("13.00"), new ItemPedido(cafe, Tamanho.MEDIO, 2).subtotal());
        assertEquals(new BigDecimal("8.00"), new ItemPedido(cafe, Tamanho.GRANDE, 1).subtotal());
    }

    @Test
    @DisplayName("a lista de itens devolvida não pode ser alterada")
    void listaImutavel() {
        Comanda comanda = new Comanda(1);
        comanda.adicionar(new ItemPedido(cafe, Tamanho.PEQUENO, 1));

        assertThrows(UnsupportedOperationException.class, () -> comanda.getItens().clear());
    }

    @Test
    @DisplayName("comanda fechada não aceita itens")
    void comandaFechada() {
        Comanda comanda = new Comanda(1);
        comanda.fechar();

        assertThrows(IllegalStateException.class,
                () -> comanda.adicionar(new ItemPedido(cafe, Tamanho.PEQUENO, 1)));
        assertEquals("fechada, aguardando pagamento", comanda.descricaoDaSituacao());
    }

    @Test
    @DisplayName("a impressão usa o text block")
    void impressao() {
        Comanda comanda = new Comanda(7);
        comanda.adicionar(new ItemPedido(cafe, Tamanho.GRANDE, 2));

        assertEquals("""
                ===== MESA 7 =====
                2 × Café grande: R$ 16.00
                Total: R$ 16.00
                Situação: aberta, aceitando pedidos""", comanda.imprimir());
    }
}
