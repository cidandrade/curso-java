package br.com.cidandrade.aulas.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Pedido da cafeteria. Guarda os itens, calcula o total e controla
 * a própria situação: só um pedido ABERTO aceita itens; só um pedido
 * ABERTO e com itens pode ser pago; um pedido PAGO não pode ser cancelado
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Pedido {

    private final List<Item> itens = new ArrayList<>();
    private Situacao situacao = Situacao.ABERTO;

    /**
     * Acrescenta um item ao pedido
     *
     * @param produto produto pedido
     * @param quantidade quantidade pedida
     * @throws IllegalStateException se o pedido não estiver ABERTO
     */
    public void adicionar(Produto produto, int quantidade) {
        exigirAberto("acrescentar itens");
        itens.add(new Item(produto, quantidade));
    }

    /**
     * @return soma dos subtotais dos itens; zero se o pedido estiver vazio
     */
    public BigDecimal total() {
        return itens.stream()
                .map(Item::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Marca o pedido como pago
     *
     * @throws IllegalStateException se o pedido não estiver ABERTO ou estiver vazio
     */
    public void pagar() {
        exigirAberto("pagar");
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível pagar um pedido vazio");
        }
        situacao = Situacao.PAGO;
    }

    /**
     * Cancela o pedido
     *
     * @throws IllegalStateException se o pedido já estiver PAGO
     */
    public void cancelar() {
        if (situacao == Situacao.PAGO) {
            throw new IllegalStateException("Não é possível cancelar um pedido pago");
        }
        situacao = Situacao.CANCELADO;
    }

    /**
     * @return os itens do pedido, numa lista que não pode ser alterada
     */
    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public Situacao getSituacao() {
        return situacao;
    }

    private void exigirAberto(String acao) {
        if (situacao != Situacao.ABERTO) {
            throw new IllegalStateException(
                    "Não é possível " + acao + " num pedido " + situacao);
        }
    }
}
