package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.classes.fabrica.CafeFabricaFW;
import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;

public class PedidoFW {

    private final CafeFW cafe;

    public static PedidoFW de(SaborDeCafe sabor,
            ArteNoCafe arte, int numeroDaMesa) {
        CafeFW cafe = CafeFabricaFW.fazCafe(sabor, arte);
        System.out.println(String.format(
                "Servindo a mesa '%d'.",
                numeroDaMesa));
        return new PedidoFW(cafe);
    }

    private PedidoFW(CafeFW cafe) {
        this.cafe = cafe;
    }

}
