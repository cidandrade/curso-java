package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.classes.fabrica.CafeFabrica;
import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;

public class Pedido {

    private final Cafe cafe;

    public static Pedido de(SaborDeCafe sabor,
            ArteNoCafe arte, int numeroDaMesa) {
        Cafe cafe = CafeFabrica.fazCafe(sabor, arte);
        System.out.println(String.format(
                "Servindo a mesa '%d'.",
                numeroDaMesa));
        return new Pedido(cafe);
    }

    private Pedido(Cafe cafe) {
        this.cafe = cafe;
    }

}
