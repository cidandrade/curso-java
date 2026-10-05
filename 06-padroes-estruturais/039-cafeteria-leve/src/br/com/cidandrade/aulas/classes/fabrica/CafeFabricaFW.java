package br.com.cidandrade.aulas.classes.fabrica;

import br.com.cidandrade.aulas.classes.CafeFW;
import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;

public class CafeFabricaFW {

    private static final HashMap<SaborDeCafe, CafeFW> mapaDeCafe = new HashMap<>();
    //private static final List<Cafe> listaDeCafe = new ArrayList<>();

    public static CafeFW fazCafe(SaborDeCafe sabor, ArteNoCafe arte) {
        CafeFW cafe = mapaDeCafe.get(sabor);
        if (cafe == null) {
            cafe = new CafeFW(sabor);
            mapaDeCafe.put(sabor, cafe);
        }
        System.out.println(String.format(
                "Fazendo '%s' com arte '%s'.",
                cafe.getSabor(), cafe.aplicarArte(arte)));
        return cafe;
    }

    public static int getQtdeCafes() {
        return mapaDeCafe.size();
    }
}
