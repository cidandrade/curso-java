package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.cardapio.Comanda;
import br.com.cidandrade.aulas.cardapio.ItemPedido;
import br.com.cidandrade.aulas.cardapio.Produto;
import br.com.cidandrade.aulas.cardapio.Tamanho;
import br.com.cidandrade.aulas.pagamento.Cartao;
import br.com.cidandrade.aulas.pagamento.Pagamento;
import br.com.cidandrade.aulas.pagamento.Pagamentos;
import java.math.BigDecimal;

/**
 * Aplicação: uma comanda paga com cartão parcelado
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class CafeteriaApp {

    public static void main(String[] args) {
        // var (Java 10): o tipo vem do lado direito. Use quando ele for óbvio
        var cappuccino = new Produto("Cappuccino", new BigDecimal("9.00"));
        var croissant = new Produto("Croissant", new BigDecimal("7.50"));
        System.out.println(cappuccino);   // toString gerado pelo record

        var comanda = new Comanda(4);
        comanda.adicionar(new ItemPedido(cappuccino, Tamanho.GRANDE, 2));
        comanda.adicionar(new ItemPedido(croissant, Tamanho.PEQUENO, 1));
        comanda.fechar();
        System.out.println(comanda.imprimir());

        Pagamento pagamento = new Cartao("Visa", 3);
        System.out.println("Pagamento: " + Pagamentos.descrever(pagamento)
                + ", taxa de R$ " + pagamento.taxa(comanda.total()));
    }
}
