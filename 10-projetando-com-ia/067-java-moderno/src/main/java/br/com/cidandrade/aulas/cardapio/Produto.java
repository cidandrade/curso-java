package br.com.cidandrade.aulas.cardapio;

import java.math.BigDecimal;

/**
 * Produto do cardápio. Um record é uma classe imutável cujo construtor,
 * acessores (nome(), preco()), equals, hashCode e toString o compilador
 * gera a partir dos componentes declarados
 *
 * @param nome nome do produto
 * @param preco preço do tamanho pequeno
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public record Produto(String nome, BigDecimal preco) {

    /**
     * Construtor compacto: valida os componentes antes de o compilador
     * atribuí-los aos campos
     */
    public Produto {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (preco == null || preco.signum() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }
    }
}
