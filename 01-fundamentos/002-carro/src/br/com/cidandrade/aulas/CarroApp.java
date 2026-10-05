package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Carro;

/**
 * Aplicação de exemplo sobre classes
 *
 * @author cidandrade
 * @version 1.0.22.10.17 Versão definitiva
 */
public class CarroApp {

    public static void main(String[] args) {
        Carro carro1 = new Carro();
        carro1.mudarMarcha(1);
        carro1.aumentarVelocidade(10);
        carro1.mudarMarcha(2);
        carro1.aumentarVelocidade(20);
        carro1.mudarMarcha(3);
        carro1.aumentarVelocidade(30);
        carro1.mudarMarcha(4);
        carro1.aumentarVelocidade(30);
        carro1.frear(10);
        carro1.mudarMarcha(5);
        carro1.aumentarVelocidade(40);
        carro1.imprimirEstadoDoCarro();

        Carro carro2 = new Carro();
        carro2.mudarMarcha(1);
        carro2.aumentarVelocidade(10);
        carro2.mudarMarcha(2);
        carro2.aumentarVelocidade(15);
        carro2.mudarMarcha(3);
        carro2.aumentarVelocidade(20);
        carro2.mudarMarcha(4);
        carro2.aumentarVelocidade(20);
        carro2.frear(15);
        carro2.mudarMarcha(5);
        carro2.aumentarVelocidade(30);
        carro2.imprimirEstadoDoCarro();
    }

}
