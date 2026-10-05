package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.ArvoreDeNatal;
import br.com.cidandrade.aulas.classes.Guirlanda;
import br.com.cidandrade.aulas.classes.Luzinhas;
import br.com.cidandrade.aulas.ifaces.DecoracaoArvoreDeNatal;
import br.com.cidandrade.util.Mensagem;

public class NatalApp {

    public static void main(String[] args) {
        DecoracaoArvoreDeNatal arvore = 
                new Guirlanda(
                        new Guirlanda(
                                new Luzinhas(new ArvoreDeNatal())));
        Mensagem.mensagem(arvore.decoracao());
    }

}
