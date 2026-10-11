package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * Pagamento por Pix: cobra e devolve
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Pix implements FormaPagamento, Estornavel {

    @Override
    public String pagar(BigDecimal valor) {
        return "Pix: recebidos R$ " + valor;
    }

    @Override
    public String estornar(BigDecimal valor) {
        return "Pix: devolvidos R$ " + valor;
    }
}
