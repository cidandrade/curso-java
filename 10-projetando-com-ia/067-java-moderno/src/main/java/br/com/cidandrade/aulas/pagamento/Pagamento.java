package br.com.cidandrade.aulas.pagamento;

import java.math.BigDecimal;

/**
 * Interface selada (Java 17): só os tipos listados em permits podem
 * implementá-la. O conjunto de formas de pagamento fica fechado e
 * conhecido, e o compilador pode conferir se todas foram tratadas
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public sealed interface Pagamento permits Cartao, Pix, Dinheiro {

    /**
     * @param valor valor pago
     * @return taxa cobrada da cafeteria por esta forma de pagamento
     */
    BigDecimal taxa(BigDecimal valor);
}
