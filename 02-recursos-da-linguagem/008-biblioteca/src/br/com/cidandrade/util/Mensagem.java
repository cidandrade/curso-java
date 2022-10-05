package br.com.cidandrade.util;

import javax.swing.JOptionPane;

/**
 *
 * @author cidandrade
 */
public class Mensagem {

    public static void mensagem(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static void mensagem(int msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static void mensagem(StringBuilder sb) {
        JOptionPane.showMessageDialog(null, sb);
    }

    public static void mensagem(Object o) {
        JOptionPane.showMessageDialog(null, o);
    }

    public static void msgErro(String msg) {
        JOptionPane.showMessageDialog(null,
                msg, "ERRO",
                JOptionPane.ERROR_MESSAGE);
    }
}
