package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * Item de um pedido: nome, preço unitário e quantidade
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public final class Item {

    private final String nome;
    private final BigDecimal preco;
    private final int quantidade;

    public Item(String nome, BigDecimal preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public BigDecimal subtotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
