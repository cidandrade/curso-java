package br.com.cidandrade.aulas.classes;

import java.math.BigDecimal;

/**
 * Produto vendido pela cafeteria. É imutável: depois de criado,
 * nome e preço não mudam
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public final class Produto {

    private final String nome;
    private final BigDecimal preco;

    /**
     * Cria um produto
     *
     * @param nome nome do produto; não pode ser nulo nem em branco
     * @param preco preço unitário; deve ser maior que zero
     * @throws IllegalArgumentException se o nome ou o preço forem inválidos
     */
    public Produto(String nome, BigDecimal preco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + preco + ")";
    }
}
