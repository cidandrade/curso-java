package br.com.cidandrade.aulas.sp;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.aulas.interfaces.ItensDoEnsaio;

public class FemininoSP extends Ensaio {

    ItensDoEnsaio itens;

    public FemininoSP(ItensDoEnsaio itens) {
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
