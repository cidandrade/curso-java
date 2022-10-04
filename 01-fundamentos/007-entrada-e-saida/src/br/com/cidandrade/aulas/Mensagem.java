package br.com.cidandrade.aulas;

import javax.swing.JOptionPane;

/**
 *
 * @author cidandrade
 */
public class Mensagem {

    public static void mensagemDeErro(String msg) {
        JOptionPane.showMessageDialog(null, msg,
                "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemDeInformacao(String msg, String titulo) {
        JOptionPane.showMessageDialog(null,
                msg, titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensagemBasica(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
