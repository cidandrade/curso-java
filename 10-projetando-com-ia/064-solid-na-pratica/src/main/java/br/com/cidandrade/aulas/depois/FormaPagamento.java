package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * [O] Ponto de extensão: uma forma de pagamento nova é uma classe nova
 * que implementa esta interface, sem alterar o Caixa
 * [I] Interface pequena: só o que toda forma de pagamento sabe fazer
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public interface FormaPagamento {

    /**
     * Cobra o valor
     *
     * @param valor valor a cobrar
     * @return texto do comprovante
     */
    String pagar(BigDecimal valor);
}
