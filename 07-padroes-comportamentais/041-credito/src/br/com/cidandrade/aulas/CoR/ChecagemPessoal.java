package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

/**
 *
 * @author cidandrade
 */
public class ChecagemPessoal extends CoRRequisicao {

    public ChecagemPessoal(Requisicao requisicao) {
        this.proximo = new ChecagemSalario(requisicao);
        this.requisicao = requisicao;
    }

    private boolean testeLocal() {
        boolean retorno = false;
        if (requisicao.getValor() > 50_000) {
            System.out.println("Valor muito alto para Crédito Pessoal");
        } else {
            retorno = true;
        }
        return retorno;
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

}
