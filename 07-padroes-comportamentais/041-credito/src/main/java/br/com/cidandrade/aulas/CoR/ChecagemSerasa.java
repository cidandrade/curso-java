package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;
import br.com.cidandrade.aulas.classes.SerasaAutorizacao;

public class ChecagemSerasa extends CoRRequisicao {

    public ChecagemSerasa(Requisicao requisicao) {
        this.proximo = new ChecagemPorTipo(requisicao);
        this.requisicao = requisicao;
    }

    private boolean testeLocal() {
        return SerasaAutorizacao.autorizado(requisicao);
    }

    @Override
    public boolean requisicaoAutorizada() {
        boolean autorizado = false;
        if (testeLocal()) {
            if (proximo == null) {
                return true;
            } else {
                autorizado = proximo.requisicaoAutorizada();
            }
        } else {
            System.out.println("Negativado no Serasa");
        }
        return autorizado;
    }

}
