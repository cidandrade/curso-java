package br.com.cidandrade.aulas.fidelidade;

/**
 * Como avisar o cliente (console, e-mail, SMS...)
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@FunctionalInterface
public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
