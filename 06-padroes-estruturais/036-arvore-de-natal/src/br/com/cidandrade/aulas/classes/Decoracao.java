package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.DecoracaoArvoreDeNatal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Decoracao implements DecoracaoArvoreDeNatal {

    private DecoracaoArvoreDeNatal arvore;

    @Override
    public String decoracao() {
        return arvore.decoracao();
    }

}
