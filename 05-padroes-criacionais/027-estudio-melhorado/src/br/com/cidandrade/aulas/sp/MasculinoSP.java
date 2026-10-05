package br.com.cidandrade.aulas.sp;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.aulas.interfaces.ItensDoEnsaio;

public class MasculinoSP extends Ensaio {

    ItensDoEnsaio itens;

    public MasculinoSP(ItensDoEnsaio itens) {
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
