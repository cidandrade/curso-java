package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.DecoracaoArvoreDeNatal;

public class Luzinhas extends Decoracao {

    public Luzinhas(DecoracaoArvoreDeNatal arvore) {
        super(arvore);
    }

    @Override
    public String decoracao() {
        return super.decoracao() + " com luzinhas";
    }

}
