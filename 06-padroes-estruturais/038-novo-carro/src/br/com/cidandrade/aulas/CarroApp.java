package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.facade.CarroFacade;

public class CarroApp {

    public static void main(String[] args) {
        CarroFacade carro = new CarroFacade();
        carro.ligarMotor();
        System.out.println("");
        carro.desligarMotor();
    }

}
