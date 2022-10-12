package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Cafeteria;
import br.com.cidandrade.aulas.classes.CafeteriaFW;
import br.com.cidandrade.aulas.classes.fabrica.CafeFabrica;
import br.com.cidandrade.aulas.classes.fabrica.CafeFabricaFW;
import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import br.com.cidandrade.util.Mensagem;

public class CafeApp {

    public static void main(String[] args) {
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.DA_CASA, 5);
        cafeteria.novoPedido(SaborDeCafe.AFFOGATO,
                ArteNoCafe.FRANCESA, 7);
        cafeteria.novoPedido(SaborDeCafe.ESPRESSO,
                ArteNoCafe.ESPINHA, 1);
        cafeteria.novoPedido(SaborDeCafe.LATTE,
                ArteNoCafe.DISNEY, 3);
        cafeteria.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.GATO, 2);
        cafeteria.novoPedido(SaborDeCafe.ESPRESSO,
                ArteNoCafe.ESPINHA, 8);
        cafeteria.novoPedido(SaborDeCafe.AFFOGATO,
                ArteNoCafe.DA_CASA, 4);
        cafeteria.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.DISNEY, 10);
        cafeteria.novoPedido(SaborDeCafe.LATTE,
                ArteNoCafe.COELHINHO, 6);
        cafeteria.novoPedido(SaborDeCafe.FRAPPUCCINO,
                ArteNoCafe.DISNEY, 9);
        Mensagem.mensagem("Objetos Pedido sem FlyWeight: "
                + Cafeteria.getNumeroDePedidos());
        Mensagem.mensagem("Objetos Cafe sem FlyWeight: "
                + CafeFabrica.getQtdeCafes());

        CafeteriaFW cafeteriaFw = new CafeteriaFW();
        cafeteriaFw.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.DA_CASA, 5);
        cafeteriaFw.novoPedido(SaborDeCafe.AFFOGATO,
                ArteNoCafe.FRANCESA, 7);
        cafeteriaFw.novoPedido(SaborDeCafe.ESPRESSO,
                ArteNoCafe.ESPINHA, 1);
        cafeteriaFw.novoPedido(SaborDeCafe.LATTE,
                ArteNoCafe.DISNEY, 3);
        cafeteriaFw.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.GATO, 2);
        cafeteriaFw.novoPedido(SaborDeCafe.ESPRESSO,
                ArteNoCafe.ESPINHA, 8);
        cafeteriaFw.novoPedido(SaborDeCafe.AFFOGATO,
                ArteNoCafe.DA_CASA, 4);
        cafeteriaFw.novoPedido(SaborDeCafe.CAPPUCCINO,
                ArteNoCafe.DISNEY, 10);
        cafeteriaFw.novoPedido(SaborDeCafe.LATTE,
                ArteNoCafe.COELHINHO, 6);
        cafeteriaFw.novoPedido(SaborDeCafe.FRAPPUCCINO,
                ArteNoCafe.DISNEY, 9);
        Mensagem.mensagem("Objetos Pedido com FlyWeight: "
                + CafeteriaFW.getNumeroDePedidos());
        Mensagem.mensagem("Objetos Cafe com FlyWeight: "
                + CafeFabricaFW.getQtdeCafes());

        System.exit(0);
    }

}
