package br.com.cidandrade.aulas;

/**
 * Interface com métodos que devem ser implementados 
 * 
 * @author cidandrade
 */
public interface InterfaceCarro {

    void mudaMarcha(int novaMarcha);

    void aumentaVelocidade(int incremento);

    void freia(int decremento);

    void imprimeEstadoDoCarro();
}
