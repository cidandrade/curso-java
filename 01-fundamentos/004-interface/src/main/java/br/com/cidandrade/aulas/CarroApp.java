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

        SUV suv1 = new SUV();
        suv1.mudaMarcha(1);
        suv1.aumentaVelocidade(10);
        suv1.mudaMarcha(2);
        suv1.aumentaVelocidade(25);
        suv1.mudaMarcha(3);
        suv1.aumentaVelocidade(30);
        suv1.mudaMarcha(4);
        suv1.aumentaVelocidade(40);
        suv1.freia(5);
        suv1.mudaMarcha(5);
        suv1.aumentaVelocidade(40);
        suv1.imprimeEstadoDoCarro();
    }

}
