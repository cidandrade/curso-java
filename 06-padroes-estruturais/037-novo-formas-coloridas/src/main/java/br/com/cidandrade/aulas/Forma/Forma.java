package br.com.cidandrade.aulas.Forma;

import br.com.cidandrade.aulas.Cor.Cor_Ponte;

public abstract class Forma {

    public Cor_Ponte cor;

    public Forma(Cor_Ponte cor) {
        this.cor = cor;
    }

    public Forma() {
    }

    public void setCor(Cor_Ponte cor) {
        this.cor = cor;
    }

    abstract public String getForma();
}
