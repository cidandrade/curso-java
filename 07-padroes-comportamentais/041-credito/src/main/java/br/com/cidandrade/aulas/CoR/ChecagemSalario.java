package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.classes.Requisicao;

public class ChecagemSalario extends CoRRequisicao {

    public ChecagemSalario(Requisicao requisicao) {
        this.proximo = null;
        this.requisicao = requisicao;
    }

    private boolean testeLocal() {
        boolean retorno = true;
        float indice = 0;
        switch (requisicao.getTipo()) {
            case CONSIGNADO:
                indice = 8F;
                break;
            case GARANTIA_CARRO:
                indice = 10F;
                break;
            case GARANTIA_IMOVEL:
                indice = 20F;
                break;
            case PESSOAL:
                indice = 6F;
                break;
        }
        if (requisicao.getValor()
                > (indice * requisicao.getCliente().getSalario())) {
            System.out.println("Valor muito alto para o salário");
            retorno = false;
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
