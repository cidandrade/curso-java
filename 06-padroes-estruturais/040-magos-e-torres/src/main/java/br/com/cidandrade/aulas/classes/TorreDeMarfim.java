package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.TorreDaMagia;
import br.com.cidandrade.util.Mensagem;

public class TorreDeMarfim implements TorreDaMagia {

    @Override
    public void entrar(Mago mago) {
        Mensagem.mensagem(mago.toString() + " entrou na Torre de Marfim");
    }

}
