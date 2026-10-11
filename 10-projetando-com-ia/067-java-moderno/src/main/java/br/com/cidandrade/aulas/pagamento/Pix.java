package br.com.cidandrade.aulas.pagamento;

import java.math.BigDecimal;

/**
 * Pix: sem taxa
 *
 * @param chave chave Pix da cafeteria
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public record Pix(String chave) implements Pagamento {

    @Override
    public BigDecimal taxa(BigDecimal valor) {
        return BigDecimal.ZERO;
    }
}
