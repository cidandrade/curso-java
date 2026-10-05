package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

public class ChecagemConsignado extends CoRRequisicao {

    public ChecagemConsignado(Requisicao requisicao) {
        this.proximo = new ChecagemSalario(requisicao);
        this.requisicao = requisicao;
    }

    private boolean testeLocal() {
        boolean retorno = false;
        if (requisicao.getValor() < 3_000) {
            System.out.println("Valor muito baixo para Crédito "
                    + "Consignado");
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
