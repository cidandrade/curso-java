package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [S] Só guarda os itens e calcula o total. Não cobra, não imprime,
 * não notifica
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Pedido {

    private final List<Item> itens = new ArrayList<>();

    public void adicionar(String nome, BigDecimal preco, int quantidade) {
        itens.add(new Item(nome, preco, quantidade));
    }

    public BigDecimal total() {
        return itens.stream()
                .map(Item::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }
}
