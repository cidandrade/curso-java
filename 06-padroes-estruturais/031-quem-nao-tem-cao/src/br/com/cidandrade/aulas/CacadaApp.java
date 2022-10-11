package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Cachorro;
import br.com.cidandrade.aulas.ifaces.Gato;

public class CacadaApp {

    public static void main(String[] args) {
        Gato mimi = new Siames();
        Cachorro rex = new Perdigueiro();
        GatoAdaptador mimi_Rex = new GatoAdaptador(mimi);

        Cachorro[] matilha = {rex, mimi_Rex};
        for (Cachorro cao : matilha) {
            cao.latir();
            cao.perseguir();
        }
    }
}
