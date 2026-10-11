package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.antes.CaixaCafeteria;
import br.com.cidandrade.aulas.depois.Caixa;
import br.com.cidandrade.aulas.depois.Cartao;
import br.com.cidandrade.aulas.depois.NotificadorEmail;
import br.com.cidandrade.aulas.depois.Pedido;
import java.math.BigDecimal;

/**
 * Aplicação: fecha o mesmo pedido com o código de antes e com o de depois.
 * A saída é parecida; o que muda é o desenho
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class SolidApp {

    public static void main(String[] args) {
        System.out.println("===== ANTES =====");
        CaixaCafeteria antes = new CaixaCafeteria();
        antes.adicionar("Café", new BigDecimal("5.00"), 2);
        antes.adicionar("Pão de queijo", new BigDecimal("4.50"), 3);
        antes.pagar("cartao");

        System.out.println();
        System.out.println("===== DEPOIS =====");
        Pedido pedido = new Pedido();
        pedido.adicionar("Café", new BigDecimal("5.00"), 2);
        pedido.adicionar("Pão de queijo", new BigDecimal("4.50"), 3);
        Caixa caixa = new Caixa(new NotificadorEmail("cliente@exemplo.com"));
        System.out.println(caixa.fechar(pedido, new Cartao()));
    }
}
