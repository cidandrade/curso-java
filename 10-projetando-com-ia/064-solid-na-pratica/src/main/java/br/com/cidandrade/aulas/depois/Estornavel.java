package br.com.cidandrade.aulas.depois;

import java.math.BigDecimal;

/**
 * [I] e [L] O estorno fica numa interface separada. Só implementa quem
 * realmente sabe estornar; assim nenhuma classe precisa lançar
 * UnsupportedOperationException e toda FormaPagamento cumpre o contrato
 * inteiro
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public interface Estornavel {

    /**
     * Devolve o valor ao cliente
     *
     * @param valor valor a devolver
     * @return texto do comprovante de estorno
     */
    String estornar(BigDecimal valor);
}
