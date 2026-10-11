package br.com.cidandrade.aulas.depois;

/**
 * [S] Só monta o texto do recibo. Devolve uma String em vez de imprimir:
 * quem chama decide onde mostrar, e o teste consegue conferir o conteúdo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class GeradorRecibo {

    public String gerar(Pedido pedido) {
        StringBuilder sb = new StringBuilder("----- RECIBO -----\n");
        for (Item item : pedido.getItens()) {
            sb.append(item.getQuantidade()).append(" × ").append(item.getNome())
                    .append(": R$ ").append(item.subtotal()).append('\n');
        }
        return sb.append("Total: R$ ").append(pedido.total()).toString();
    }
}
