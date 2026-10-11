package br.com.cidandrade.aulas.depois;

/**
 * Implementação "de verdade" do Notificador (aqui, só imprime)
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class NotificadorEmail implements Notificador {

    private final String destinatario;

    public NotificadorEmail(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public void notificar(String mensagem) {
        System.out.println("[e-mail para " + destinatario + "] " + mensagem);
    }
}
