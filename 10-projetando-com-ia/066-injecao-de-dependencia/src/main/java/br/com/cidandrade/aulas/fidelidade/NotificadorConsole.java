package br.com.cidandrade.aulas.fidelidade;

/**
 * Notificador que escreve no console
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class NotificadorConsole implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("[para " + cliente.getNome() + "] " + mensagem);
    }
}
