package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.bebida.Cafe;
import br.com.cidandrade.aulas.bebida.Cha;

// DP Method
public class Cafeteria {

    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        cafe.prepararBebida();

        Cha cha = new Cha();
        cha.prepararBebida();

    }

}
