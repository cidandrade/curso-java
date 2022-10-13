package br.com.cidandrade.util;

import java.text.DecimalFormat;

/**
 *
 * @author cidandrade
 */
public class Formatacao {

    public static DecimalFormat formatadorDecimal() {
        return new DecimalFormat("#,##0.00");
    }
}
