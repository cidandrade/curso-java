package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import java.util.ArrayList;

public class CafeteriaFW {

    private static final ArrayList<PedidoFW> pedidos = new ArrayList<>();

    public void novoPedido(SaborDeCafe sabor, ArteNoCafe arte, int mesa) {
        pedidos.add(PedidoFW.de(sabor, arte, mesa));
    }

    public static int getNumeroDePedidos() {
        return pedidos.size();
    }
}
