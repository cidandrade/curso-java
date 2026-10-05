package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

public class ChecagemImovel extends CoRRequisicao {

    public ChecagemImovel(Requisicao requisicao) {
        this.proximo = new ChecagemSalario(requisicao);
        this.requisicao = requisicao;
    }

    private boolean testeLocal() {
        boolean retorno = false;
        if (requisicao.getValor() < 30_000) {
            System.out.println("Valor muito baixo para Crédito "
                    + "com imóvel em garantia");
        } else if (requisicao.getValor()
                > (35 * requisicao.getCliente().getSalario())) {
            System.out.println("Valor muito alto para valor "
                    + "presumido do imóvel");
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
