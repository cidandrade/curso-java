package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Mago;
import br.com.cidandrade.aulas.classes.TorreDeMarfim;
import br.com.cidandrade.aulas.proxies.TorreDaMagiaProxy;

public class MagoApp {

    public static void main(String[] args) {
        TorreDaMagiaProxy proxy
                = new TorreDaMagiaProxy(new TorreDeMarfim());
        proxy.entrar(new Mago("Mago vermelho"));
        proxy.entrar(new Mago("Mago Branco"));
        proxy.entrar(new Mago("Mago Negro"));
        proxy.entrar(new Mago("Mago Verde"));
        proxy.entrar(new Mago("Mago Cinza"));
        
        System.exit(0);
    }
}
