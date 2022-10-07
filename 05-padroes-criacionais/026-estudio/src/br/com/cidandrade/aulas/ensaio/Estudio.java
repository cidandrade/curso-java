package br.com.cidandrade.aulas.ensaio;

import br.com.cidandrade.aulas.enums.TipoEnsaio;

public abstract class Estudio {

    public Ensaio contrataEnsaio(TipoEnsaio tipo) {
        Ensaio ensaio = criaEnsaio(tipo);
        ensaio.preparaFundo();
        ensaio.separaFigurino();
        ensaio.separaAcessorios();
        ensaio.itensDeAmbiente();
        return ensaio;
    }

    public abstract Ensaio criaEnsaio(TipoEnsaio tipo);
}
