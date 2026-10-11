package br.com.cidandrade.aulas.cardapio;

import java.math.BigDecimal;

/**
 * Tamanhos de bebida. O acréscimo é um atributo de cada constante:
 * um tamanho novo já nasce com o seu acréscimo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public enum Tamanho {
    PEQUENO("0.00"), MEDIO("1.50"), GRANDE("3.00");

    private final BigDecimal acrescimo;

    Tamanho(String acrescimo) {
        this.acrescimo = new BigDecimal(acrescimo);
    }

    public BigDecimal acrescimo() {
        return acrescimo;
    }
}
