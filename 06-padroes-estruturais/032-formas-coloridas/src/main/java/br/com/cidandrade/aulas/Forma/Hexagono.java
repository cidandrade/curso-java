package br.com.cidandrade.aulas.Forma;

import br.com.cidandrade.aulas.Cor.Cor_Ponte;

public class Hexagono extends Forma {

    public Hexagono(Cor_Ponte cor) {
        super(cor);
    }

    @Override
    public String getForma() {
        return "Hexágono " + cor.getCor();
    }

}
