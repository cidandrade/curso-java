package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import java.util.ArrayList;

public class Cafeteria{

    private static final ArrayList<Pedido> pedidos = new ArrayList<>();

    public void novoPedido(SaborDeCafe sabor, ArteNoCafe arte, int mesa) {
        pedidos.add(Pedido.de(sabor, arte, mesa));
    }

    public static int getNumeroDePedidos() {
        return pedidos.size();
    }
}
