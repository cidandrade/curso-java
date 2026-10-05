package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Gato;
import br.com.cidandrade.util.Mensagem;

public class Siames implements Gato {

    @Override
    public void miar() {
        Mensagem.mensagem("Miau, miau");
    }

    @Override
    public void perseguir() {
        Mensagem.mensagem("Correr atrás de caça, se estiver afim, claro");
    }

}
