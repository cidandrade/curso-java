package br.com.cidandrade.aulas.pagamento;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Cartão: taxa de 2% à vista e 4% parcelado, em até 12 vezes
 *
 * @param bandeira bandeira do cartão
 * @param parcelas número de parcelas, de 1 a 12
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public record Cartao(String bandeira, int parcelas) implements Pagamento {

    public Cartao {
        if (parcelas < 1 || parcelas > 12) {
            throw new IllegalArgumentException("Parcelas devem ir de 1 a 12");
        }
    }

    @Override
    public BigDecimal taxa(BigDecimal valor) {
        BigDecimal percentual = new BigDecimal(parcelas == 1 ? "0.02" : "0.04");
        return valor.multiply(percentual).setScale(2, RoundingMode.HALF_UP);
    }
}
