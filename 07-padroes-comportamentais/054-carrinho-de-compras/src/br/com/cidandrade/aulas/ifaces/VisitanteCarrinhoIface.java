package br.com.cidandrade.aulas.ifaces;

import br.com.cidandrade.aulas.classes.Fruta;
import br.com.cidandrade.aulas.classes.Livro;

public interface VisitanteCarrinhoIface {

    float visitar(Livro livro);

    float visitar(Fruta fruta);
}
