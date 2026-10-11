package br.com.cidandrade.aulas.classes;

import java.math.BigDecimal;

/**
 * Item de um pedido: um produto e a quantidade pedida
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public final class Item {

    private final Produto produto;
    private final int quantidade;

    /**
     * Cria um item
     *
     * @param produto produto pedido; não pode ser nulo
     * @param quantidade quantidade pedida; deve ser maior que zero
     * @throws IllegalArgumentException se o produto ou a quantidade forem inválidos
     */
    public Item(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto é obrigatório");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * @return preço do produto multiplicado pela quantidade
     */
    public BigDecimal subtotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return quantidade + " × " + produto;
    }
}
