package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * Vale-refeição: cobra, mas não tem estorno. Por isso implementa só
 * FormaPagamento, e o compilador impede que alguém tente estorná-lo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Vale implements FormaPagamento {

    @Override
    public String pagar(BigDecimal valor) {
        return "Vale-refeição: debitados R$ " + valor;
    }
}
