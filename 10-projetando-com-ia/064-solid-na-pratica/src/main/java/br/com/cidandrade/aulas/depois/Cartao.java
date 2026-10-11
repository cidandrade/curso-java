package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * Pagamento no cartão: cobra e estorna
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Cartao implements FormaPagamento, Estornavel {

    @Override
    public String pagar(BigDecimal valor) {
        return "Cartão: cobrados R$ " + valor;
    }

    @Override
    public String estornar(BigDecimal valor) {
        return "Cartão: estornados R$ " + valor;
    }
}
