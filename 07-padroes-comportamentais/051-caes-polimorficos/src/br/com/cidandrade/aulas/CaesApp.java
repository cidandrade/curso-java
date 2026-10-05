package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Labrador;
import br.com.cidandrade.aulas.classes.comport.DietaProteina;

public class CaesApp {

    public static void main(String[] args) {
        Labrador rex = new Labrador();
        rex.comer();
        rex.latir();

        rex.setDieta(new DietaProteina());
        rex.comer();
        rex.latir();

        System.exit(0);
    }

}
