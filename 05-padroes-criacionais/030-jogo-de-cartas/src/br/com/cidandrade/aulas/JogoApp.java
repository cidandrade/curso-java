package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class JogoApp {

    public static void main(String[] args) {
        Baralho baralho, outroBaralho;
        Mao mao, outraMao;
        baralho = Baralho.obterBaralho();
        Mensagem.mensagem("Baralho 1 tem "
                + baralho.quantasCartas() + " cartas\n"
                + "Tirarei cinco delas");
        mao = baralho.retiraCartas(5);
        mao.imprime();
        Mensagem.mensagem("Sobraram "
                + baralho.quantasCartas() + " cartas no baralho 1");
        outroBaralho = Baralho.obterBaralho();
        Mensagem.mensagem("O segundo baralho tem "
                + outroBaralho.quantasCartas() + " cartas");
        outraMao = outroBaralho.retiraCartas(5);
        outraMao.imprime();
        Mensagem.mensagem("Sobraram "
                + outroBaralho.quantasCartas() + " cartas no baralho 2");
        System.exit(0);
    }

}
