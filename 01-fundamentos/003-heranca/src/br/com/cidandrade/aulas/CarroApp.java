package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Carro;
import br.com.cidandrade.aulas.classes.SUV;

/**
 * Aplicação de exemplo sobre classes
 *
 * @author profandrade@gmail.com
 * @version 1.0.22.10.17 Definitiva
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

        SUV suv1 = new SUV();
        suv1.mudarMarcha(1);
        suv1.aumentarVelocidade(10);
        suv1.mudarMarcha(2);
        suv1.aumentarVelocidade(25);
        suv1.mudarMarcha(3);
        suv1.aumentarVelocidade(30);
        suv1.mudarMarcha(4);
        suv1.aumentarVelocidade(40);
        suv1.frear(5);
        suv1.mudarMarcha(5);
        suv1.aumentarVelocidade(40);
        suv1.imprimirEstadoDoCarro();
    }

}
