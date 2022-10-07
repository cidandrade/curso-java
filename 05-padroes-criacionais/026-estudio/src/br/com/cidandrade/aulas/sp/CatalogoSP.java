package br.com.cidandrade.aulas.sp;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.util.Mensagem;

public class CatalogoSP extends Ensaio {

    public CatalogoSP() {
        super.setNome("Catálogo paulistano");
    }

    @Override
    public void itensDeAmbiente() {
        Mensagem.mensagem("Música ambiente leve e "
                + "descontraída, biscoitos e água");
    }

    @Override
    public void separaAcessorios() {
        Mensagem.mensagem("Sem necessidade de acessórios");
    }

    @Override
    public void separaFigurino() {
        Mensagem.mensagem("Figurinos por conta da cliente");
    }

    @Override
    public void preparaFundo() {
        Mensagem.mensagem("Separar fundo branco");
    }

}
