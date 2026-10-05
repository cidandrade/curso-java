package br.com.cidandrade.aulas.classes;

import lombok.Setter;

@Setter
public class Radiador {

    private int limiteMaxTemperatura;

    public void acionar() {
        System.out.println("Acionar radiador");
    }

    public void verificar(int TEMPERATURA_IDEAL) {
        System.out.println("Verificar temperatura do "
                + "radiador abaixo de " + limiteMaxTemperatura);
    }

    public void parar() {
        System.out.println("Desligar radiador");
    }
}
