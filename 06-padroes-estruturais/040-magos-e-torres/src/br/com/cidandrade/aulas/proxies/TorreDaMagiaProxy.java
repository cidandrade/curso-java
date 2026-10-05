package br.com.cidandrade.aulas.proxies;

import br.com.cidandrade.aulas.classes.Mago;
import br.com.cidandrade.aulas.ifaces.TorreDaMagia;
import br.com.cidandrade.util.Mensagem;

public class TorreDaMagiaProxy implements TorreDaMagia {

    private static final int NUM_MAGOS_PERMITIDOS = 3;
    private int numMagos;
    private final TorreDaMagia torre;

    public TorreDaMagiaProxy(TorreDaMagia torre) {
        this.torre = torre;
    }

    @Override
    public void entrar(Mago mago) {
        if (numMagos < NUM_MAGOS_PERMITIDOS) {
            torre.entrar(mago);
            numMagos++;
        } else {
            Mensagem.msgErro("Entrada de " + mago 
                    + " não foi permitida");
        }
    }
}
