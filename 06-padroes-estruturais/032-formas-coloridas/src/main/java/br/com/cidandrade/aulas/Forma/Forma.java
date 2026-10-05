package br.com.cidandrade.aulas.Forma;

import br.com.cidandrade.aulas.Cor.Cor_Ponte;

public abstract class Forma {

    public Forma(Cor_Ponte cor) {
        this.cor = cor;
    }

    public Cor_Ponte cor;

    abstract public String getForma();
}
