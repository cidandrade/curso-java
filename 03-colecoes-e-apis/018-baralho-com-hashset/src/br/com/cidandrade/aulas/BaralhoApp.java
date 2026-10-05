package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class BaralhoApp {

    public static void main(String[] args) {
        Baralho baralho = new Baralho();
        Mensagem.mensagem("Baralho tem "
                + baralho.quantasCartas() + " cartas\n"
                + "Tirarei cinco delas");
        Mao mao = baralho.retiraCartas(5);
        mao.imprime();
        Mensagem.mensagem("Sobraram "
                + baralho.quantasCartas() + " cartas no baralho");
        System.exit(0);
    }

}
