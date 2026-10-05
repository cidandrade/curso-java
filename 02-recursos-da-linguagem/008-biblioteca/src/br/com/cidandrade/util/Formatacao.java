package br.com.cidandrade.util;

import java.text.DecimalFormat;

public class Formatacao {

    private static final String PADRAO_DEC = "#,##0.00";

    public static String formDecimal(double num) {
        return new DecimalFormat(PADRAO_DEC).format(num);
    }

    public static String formDecimal(float num) {
        return new DecimalFormat(PADRAO_DEC).format(num);
    }

    public static String formDecimalComPonto(double num) {
        return new DecimalFormat(PADRAO_DEC)
                .format(num).replace(",", ".");
    }

    public static String formDecimalComPonto(float num) {
        return new DecimalFormat(PADRAO_DEC)
                .format(num).replace(",", ".");
    }
}
