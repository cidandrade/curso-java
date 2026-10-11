package br.com.cidandrade.aulas.cardapio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Comanda de uma mesa. NÃO é um record: ela muda de estado (recebe itens,
 * é fechada) e tem identidade (duas comandas vazias não são "a mesma").
 * Records servem para valores; entidades continuam sendo classes
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Comanda {

    public enum Situacao { ABERTA, FECHADA, CANCELADA }

    private final int mesa;
    private final List<ItemPedido> itens = new ArrayList<>();
    private Situacao situacao = Situacao.ABERTA;

    public Comanda(int mesa) {
        this.mesa = mesa;
    }

    public void adicionar(ItemPedido item) {
        if (situacao != Situacao.ABERTA) {
            throw new IllegalStateException("Comanda " + situacao.name().toLowerCase());
        }
        itens.add(item);
    }

    public BigDecimal total() {
        return itens.stream()
                .map(ItemPedido::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void fechar() {
        situacao = Situacao.FECHADA;
    }

    public void cancelar() {
        situacao = Situacao.CANCELADA;
    }

    /**
     * @return os itens; toList() (Java 16) devolve uma lista que não pode
     * ser alterada
     */
    public List<ItemPedido> getItens() {
        return itens.stream().toList();
    }

    /**
     * switch como expressão (Java 14): devolve um valor, usa "->" (sem
     * break) e, num enum, não precisa de default. Se surgir uma situação
     * nova, o compilador acusa este switch
     *
     * @return descrição da situação
     */
    public String descricaoDaSituacao() {
        return switch (situacao) {
            case ABERTA -> "aberta, aceitando pedidos";
            case FECHADA -> "fechada, aguardando pagamento";
            case CANCELADA -> "cancelada";
        };
    }

    /**
     * Text block (Java 15): texto de várias linhas sem "\n" nem
     * concatenação; formatted() preenche os %s
     *
     * @return a comanda pronta para imprimir
     */
    public String imprimir() {
        String linhas = itens.stream()
                .map(i -> "%d × %s %s: R$ %s".formatted(i.quantidade(),
                        i.produto().nome(), i.tamanho().name().toLowerCase(), i.subtotal()))
                .collect(Collectors.joining("\n"));
        return """
                ===== MESA %d =====
                %s
                Total: R$ %s
                Situação: %s""".formatted(mesa, linhas, total(), descricaoDaSituacao());
    }
}
