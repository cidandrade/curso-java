package br.com.cidandrade.aulas.ubatuba;

import br.com.cidandrade.aulas.interfaces.ItensDoEnsaio;
import br.com.cidandrade.aulas.itens.Concha;
import br.com.cidandrade.aulas.itens.FundoTexturizado;
import br.com.cidandrade.aulas.itens.SaidaDePraia;
import br.com.cidandrade.aulas.itens.VinhoBranco;
import br.com.cidandrade.aulas.preparativo.Acessorio;
import br.com.cidandrade.aulas.preparativo.Ambiente;
import br.com.cidandrade.aulas.preparativo.Figurino;
import br.com.cidandrade.aulas.preparativo.Fundo;

public class PreparativosUbatuba implements ItensDoEnsaio {

    @Override
    public Fundo preparaFundo() {
        return new FundoTexturizado();
    }

    @Override
    public Figurino separaFigurino() {
        return new SaidaDePraia();
    }

    @Override
    public Acessorio separaAcessorios() {
        return new Concha();
    }

    @Override
    public Ambiente preparaAmbiente() {
        return new VinhoBranco();
    }
}
