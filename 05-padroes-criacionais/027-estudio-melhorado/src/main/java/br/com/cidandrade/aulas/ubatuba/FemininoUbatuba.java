package br.com.cidandrade.aulas.ubatuba;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.aulas.interfaces.ItensDoEnsaio;

public class FemininoUbatuba extends Ensaio {

    ItensDoEnsaio itens;

    public FemininoUbatuba(ItensDoEnsaio itens) {
        super.setNome("Catálogo paulistano");
        this.itens = itens;
    }

    @Override
    public void preparar() {
        fundo = itens.preparaFundo();
        figurino = itens.separaFigurino();
        acessorio = itens.separaAcessorios();
        ambiente = itens.preparaAmbiente();
    }

}
