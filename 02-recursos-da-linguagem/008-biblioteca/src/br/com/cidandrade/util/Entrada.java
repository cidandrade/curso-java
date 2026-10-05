package br.com.cidandrade.util;

import javax.swing.JOptionPane;

/**
 *
 * @author cidandrade
 */
public class Entrada {

    public static String getString(String msg) {
        return JOptionPane.showInputDialog(null, msg);
    }

    public static Float getFloat(String msg) throws NumberFormatException {
        return Float.valueOf(getString(msg).replace(',', '.'));
    }

    public static Integer getInteger(String msg) throws NumberFormatException {
        return Integer.valueOf(getString(msg));
    }

    public static Long getLong(String msg) throws NumberFormatException {
        return Long.valueOf(getString(msg));
    }

    public static boolean confirma(String msg) {
        int resp = JOptionPane.showConfirmDialog(null, 
                msg, "Confirmação", 
                JOptionPane.YES_NO_OPTION);
        return (resp == JOptionPane.YES_OPTION);
    }
}
