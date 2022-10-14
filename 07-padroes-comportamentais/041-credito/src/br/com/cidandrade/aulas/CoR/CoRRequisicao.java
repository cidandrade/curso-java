package br.com.cidandrade.aulas.CoR;

import br.com.cidandrade.aulas.CoR.ifaces.Responsabilidade;
import br.com.cidandrade.aulas.classes.Requisicao;

public abstract class CoRRequisicao implements Responsabilidade {

    protected Responsabilidade proximo;
    protected Requisicao requisicao;
}
