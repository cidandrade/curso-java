package br.com.cidandrade.aulas.Forma;

import br.com.cidandrade.aulas.Cor.Cor_Ponte;

public class Quadrado extends Forma {

    public Quadrado(Cor_Ponte cor) {
        super(cor);
    }

    @Override
    public String getForma() {
        return "Quadrado " + cor.getCor();
    }

}
