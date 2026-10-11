package br.com.cidandrade.aulas.cardapio;

import java.math.BigDecimal;

/**
 * Item de uma comanda. Também é um valor: dois itens com o mesmo produto,
 * tamanho e quantidade são iguais. Records podem ter métodos
 *
 * @param produto produto pedido
 * @param tamanho tamanho escolhido
 * @param quantidade quantidade pedida
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public record ItemPedido(Produto produto, Tamanho tamanho, int quantidade) {

    public ItemPedido {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
    }

    public BigDecimal subtotal() {
        return produto.preco().add(tamanho.acrescimo())
                .multiply(BigDecimal.valueOf(quantidade));
    }
}
