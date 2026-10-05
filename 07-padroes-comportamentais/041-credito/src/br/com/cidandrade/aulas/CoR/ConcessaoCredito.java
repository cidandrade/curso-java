package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

public class ConcessaoCredito extends CoRRequisicao {

    public ConcessaoCredito(Requisicao requisicao) {
        this.proximo = new ChecagemSerasa(requisicao);
        this.requisicao = requisicao;
    }

    public void avaliarConcessaoCredito() {
        String tipo = requisicao.getTipo().getTipo();
        if (requisicaoAutorizada()) {
            System.out.println("Crédito " + tipo + " Autorizado\n");
        } else {
            System.out.println("Crédito " + tipo + " Negado\n");
        }
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
        }
        return autorizado;
    }

    private boolean testeLocal() {
        return true;
    }
}
