package br.com.cidandrade.aulas.pagamento;

import java.math.BigDecimal;

/**
 * Dinheiro: sem taxa; calcula o troco
 *
 * @param recebido valor entregue pelo cliente
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public record Dinheiro(BigDecimal recebido) implements Pagamento {

    @Override
    public BigDecimal taxa(BigDecimal valor) {
        return BigDecimal.ZERO;
    }

    /**
     * @param total valor a pagar
     * @return troco a devolver
     * @throws IllegalArgumentException se o valor recebido não cobrir o total
     */
    public BigDecimal troco(BigDecimal total) {
        if (recebido.compareTo(total) < 0) {
            throw new IllegalArgumentException("Valor recebido menor que o total");
        }
        return recebido.subtract(total);
    }
}
