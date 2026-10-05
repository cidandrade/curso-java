package br.com.cidandrade.aulas.classes.comport;

import br.com.cidandrade.aulas.ifaces.ComportLatido;
import br.com.cidandrade.util.Mensagem;

public class Rosnado implements ComportLatido {

    @Override
    public void latir() {
        Mensagem.mensagem("Grrrrrrrrr!");
    }
    
}
