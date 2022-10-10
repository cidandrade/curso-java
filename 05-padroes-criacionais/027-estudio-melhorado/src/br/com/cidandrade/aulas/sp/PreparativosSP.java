package br.com.cidandrade.aulas.sp;

import br.com.cidandrade.aulas.interfaces.ItensDoEnsaio;
import br.com.cidandrade.aulas.itens.AcessorioVintage;
import br.com.cidandrade.aulas.itens.FigurinoVintage;
import br.com.cidandrade.aulas.itens.FundoBranco;
import br.com.cidandrade.aulas.itens.MusicaLeve;
import br.com.cidandrade.aulas.preparativo.Acessorio;
import br.com.cidandrade.aulas.preparativo.Ambiente;
import br.com.cidandrade.aulas.preparativo.Figurino;
import br.com.cidandrade.aulas.preparativo.Fundo;

public class PreparativosSP implements ItensDoEnsaio {

    @Override
    public Fundo preparaFundo() {
        return new FundoBranco();
    }

    @Override
    public Figurino separaFigurino() {
        return new FigurinoVintage();
    }

    @Override
    public Acessorio separaAcessorios() {
        return new AcessorioVintage();
    }

    @Override
    public Ambiente preparaAmbiente() {
        return new MusicaLeve();
    }

}
