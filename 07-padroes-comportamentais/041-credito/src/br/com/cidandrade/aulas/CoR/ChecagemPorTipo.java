package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

public class ChecagemPorTipo extends CoRRequisicao {

    public ChecagemPorTipo(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    @Override
    public boolean requisicaoAutorizada() {
        boolean autorizado;
        switch (requisicao.getTipo()) {
            case CONSIGNADO:
                this.proximo = new ChecagemConsignado(requisicao);
                break;
            case GARANTIA_CARRO:
                this.proximo = new ChecagemCarro(requisicao);
                break;
            case GARANTIA_IMOVEL:
                this.proximo = new ChecagemImovel(requisicao);
                break;
            case PESSOAL:
                this.proximo = new ChecagemPessoal(requisicao);
                break;
        }
        autorizado = proximo.requisicaoAutorizada();
        return autorizado;
    }
}
