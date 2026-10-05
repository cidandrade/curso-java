package br.com.cidandrade.aulas.interfaces;

import br.com.cidandrade.aulas.preparativo.Acessorio;
import br.com.cidandrade.aulas.preparativo.Ambiente;
import br.com.cidandrade.aulas.preparativo.Figurino;
import br.com.cidandrade.aulas.preparativo.Fundo;

public interface ItensDoEnsaio {

    public Fundo preparaFundo();

    public Figurino separaFigurino();

    public Acessorio separaAcessorios();

    public Ambiente preparaAmbiente();
}
