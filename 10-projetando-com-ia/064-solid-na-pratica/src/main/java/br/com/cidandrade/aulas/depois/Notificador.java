package br.com.cidandrade.aulas.depois;

/**
 * [D] Abstração da qual o Caixa depende. Por ter um único método, pode
 * ser implementada com uma expressão lambda (útil nos testes)
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@FunctionalInterface
public interface Notificador {

    void notificar(String mensagem);
}
