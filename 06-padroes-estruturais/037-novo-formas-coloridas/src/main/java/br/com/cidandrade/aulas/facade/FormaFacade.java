package br.com.cidandrade.aulas.facade;

import br.com.cidandrade.aulas.Cor.Azul;
import br.com.cidandrade.aulas.Cor.CorEnum;
import br.com.cidandrade.aulas.Cor.Verde;
import br.com.cidandrade.aulas.Forma.Forma;
import br.com.cidandrade.aulas.Forma.FormaEnum;
import br.com.cidandrade.aulas.Forma.Hexagono;
import br.com.cidandrade.aulas.Forma.Quadrado;

public class FormaFacade {

    private Forma forma;

    public Forma getForma(FormaEnum forma, CorEnum cor) {
        switch (forma) {
            case HEXAGONO:
                this.forma = new Hexagono();
                break;
            case QUADRADO:
                this.forma = new Quadrado();
                break;
        }

        switch (cor) {
            case AZUL:
                this.forma.setCor(new Azul());
                break;
            case VERDE:
                this.forma.setCor(new Verde());
                break;
        }
        
        return this.forma;
    }
}
