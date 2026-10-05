package br.com.cidandrade.aulas.classes.fabrica;

import br.com.cidandrade.aulas.classes.Cafe;
import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import java.util.ArrayList;
import java.util.List;

public class CafeFabrica {

    private static final List<Cafe> listaDeCafe = new ArrayList<>();

    public static Cafe fazCafe(SaborDeCafe sabor, ArteNoCafe arte) {
        Cafe cafe = new Cafe(sabor, arte);
        System.out.println(String.format(
                "Fazendo '%s' com arte '%s'.",
                cafe.getSabor(), cafe.getArte()));
        listaDeCafe.add(cafe);
        return cafe;
    }

    public static int getQtdeCafes() {
        return listaDeCafe.size();
    }
}
