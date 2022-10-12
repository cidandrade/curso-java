package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.Cor.Azul;
import br.com.cidandrade.aulas.Forma.Forma;
import br.com.cidandrade.aulas.Forma.Quadrado;
import br.com.cidandrade.util.Mensagem;

public class FormaApp {

    public static void main(String[] args) {
        Forma forma = new Quadrado(new Azul());
        Mensagem.mensagem(forma.getForma());
    }

}
