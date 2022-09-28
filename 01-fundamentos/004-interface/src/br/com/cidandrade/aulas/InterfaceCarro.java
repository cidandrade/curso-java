package br.com.cidandrade.aulas;

/**
 *
 * @author cidandrade
 */
public interface InterfaceCarro {

    void mudaMarcha(int novaMarcha);

    void aumentaVelocidade(int incremento);

    void freia(int decremento);

    void imprimeEstadoDoCarro();
}
