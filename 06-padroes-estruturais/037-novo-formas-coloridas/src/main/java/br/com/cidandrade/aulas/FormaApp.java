package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.Cor.CorEnum;
import br.com.cidandrade.aulas.Forma.Forma;
import br.com.cidandrade.aulas.Forma.FormaEnum;
import br.com.cidandrade.aulas.facade.FormaFacade;
import br.com.cidandrade.util.Mensagem;

public class FormaApp {

    public static void main(String[] args) {
        FormaFacade formaFacade = new FormaFacade();
        Forma forma = formaFacade.getForma(
                FormaEnum.QUADRADO, CorEnum.AZUL);
        Mensagem.mensagem(forma.getForma());
    }

}
