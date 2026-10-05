package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.DecoracaoArvoreDeNatal;

public class Guirlanda extends Decoracao {

    public Guirlanda(DecoracaoArvoreDeNatal arvore) {
        super(arvore);
    }

    @Override
    public String decoracao() {
        return super.decoracao() + " com guirlanda";
    }

}
