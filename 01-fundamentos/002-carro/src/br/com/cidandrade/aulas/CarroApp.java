package br.com.cidandrade.aulas;

/**
 * Esta classe implementa uma aplicação de exemplo sobre classes
 *
 * @author cidandrade
 */
public class CarroApp {

    public static void main(String[] args) {
        Carro carro1 = new Carro();
        carro1.mudaMarcha(1);
        carro1.aumentaVelocidade(10);
        carro1.mudaMarcha(2);
        carro1.aumentaVelocidade(20);
        carro1.mudaMarcha(3);
        carro1.aumentaVelocidade(30);
        carro1.mudaMarcha(4);
        carro1.aumentaVelocidade(30);
        carro1.freia(10);
        carro1.mudaMarcha(5);
        carro1.aumentaVelocidade(40);
        carro1.imprimeEstadoDoCarro();

        Carro carro2 = new Carro();
        carro2.mudaMarcha(1);
        carro2.aumentaVelocidade(10);
        carro2.mudaMarcha(2);
        carro2.aumentaVelocidade(15);
        carro2.mudaMarcha(3);
        carro2.aumentaVelocidade(20);
        carro2.mudaMarcha(4);
        carro2.aumentaVelocidade(20);
        carro2.freia(15);
        carro2.mudaMarcha(5);
        carro2.aumentaVelocidade(30);
        carro2.imprimeEstadoDoCarro();
    }

}
