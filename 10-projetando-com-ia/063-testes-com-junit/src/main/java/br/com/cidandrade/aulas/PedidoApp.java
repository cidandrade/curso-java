package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Pedido;
import br.com.cidandrade.aulas.classes.Produto;
import java.math.BigDecimal;

/**
 * Aplicação de demonstração: monta e paga um pedido. As regras do pedido
 * estão especificadas nos testes, em src/test/java
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class PedidoApp {

    public static void main(String[] args) {
        Produto cafe = new Produto("Café", new BigDecimal("5.00"));
        Produto paoDeQueijo = new Produto("Pão de queijo", new BigDecimal("4.50"));

        Pedido pedido = new Pedido();
        pedido.adicionar(cafe, 2);
        pedido.adicionar(paoDeQueijo, 3);
        pedido.getItens().forEach(System.out::println);
        System.out.println("Total: R$ " + pedido.total());

        pedido.pagar();
        System.out.println("Situação: " + pedido.getSituacao());

        try {
            pedido.cancelar();
        } catch (IllegalStateException e) {
            System.out.println("Regra respeitada: " + e.getMessage());
        }
    }
}
