package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Carro;

/**
 * Aplicação de exemplo sobre classes: cria dois carros e mostra que
 * cada objeto guarda o seu próprio estado
 *
 * @author profandrade@gmail.com
 * @version 2.0.0
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
        System.out.println(carro1);
        // Experimente: descomente a linha abaixo. O carro está a 120 km/h, então
        // o objeto recusa a ré e o programa para com uma IllegalStateException
        // carro1.mudarMarcha(-1);

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
        System.out.println(carro2);

        // Para engatar a ré, o carro precisa estar parado
        carro2.frear(80);
        carro2.mudarMarcha(-1);
        carro2.aumentarVelocidade(5);
        System.out.println(carro2);
    }

}
