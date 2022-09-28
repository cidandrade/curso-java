package br.com.cidandrade.aulas;

/**
 * Esta classe representa um carro
 *
 * @author cidandrade
 */
public class Carro {

    int velocidade = 0;
    int marcha = 0;

    void mudaMarcha(int novaMarcha) {
        marcha = novaMarcha;
    }

    void aumentaVelocidade(int incremento) {
        velocidade = velocidade + incremento;
    }

    void freia(int decremento) {
        velocidade = velocidade - decremento;
    }

    void imprimeEstadoDoCarro() {
        System.out.println("Carro a " + velocidade + " Km/h em "
                + marcha + "a. marcha");
    }
}
