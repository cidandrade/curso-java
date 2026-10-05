package br.com.cidandrade.aulas.classes;

/**
 * Esta classe representa um carro
 *
 * @author profandrade@gmail.com
 * @version 1.0.22.10.17 Versão definitiva
 */
public class Carro {

    private int velocidade = 0;
    private int marcha = 0;

    /**
     * Método para troca de marcha do carro
     *
     * @param novaMarcha int - Marcha que será atribuída
     */
    public void mudarMarcha(int novaMarcha) {
        marcha = novaMarcha;
    }

    /**
     * Método para acelerar o carro
     *
     * @param incremento int - O quanto será adicionado à velocidade
     */
    public void aumentarVelocidade(int incremento) {
        velocidade = velocidade + incremento;
    }

    /**
     * Método para frear o carro
     *
     * @param decremento int - O quanto será reduzido da velocidade
     */

    public void frear(int decremento) {
        velocidade = velocidade - decremento;
    }

    /**
     * Método para indicar como está o carro
     *
     */

    public void imprimirEstadoDoCarro() {
        System.out.println("Carro a " + velocidade + " Km/h em "
                + marcha + "a. marcha");
    }
}
