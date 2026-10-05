package br.com.cidandrade.aulas;

/**
 * Esta classe representa um carro
 *
 * @author cidandrade
 */
public class Carro implements InterfaceCarro {

    int velocidade = 0;
    int marcha = 0;

    @Override
    public void mudaMarcha(int novaMarcha) {
        marcha = novaMarcha;
    }

    @Override
    public void aumentaVelocidade(int incremento) {
        velocidade = velocidade + incremento;
    }

    @Override
    public void freia(int decremento) {
        velocidade = velocidade - decremento;
    }

    @Override
    public void imprimeEstadoDoCarro() {
        System.out.println("Carro a " + velocidade + " Km/h em "
                + marcha + "a. marcha");
    }
}
