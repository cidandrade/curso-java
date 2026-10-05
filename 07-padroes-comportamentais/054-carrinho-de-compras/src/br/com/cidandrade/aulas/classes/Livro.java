package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Elemento;
import br.com.cidandrade.aulas.ifaces.VisitanteCarrinhoIface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Livro implements Elemento {

    private float preco;
    private String isbn;

    @Override
    public float aceitar(VisitanteCarrinhoIface visitante) {
        return visitante.visitar(this);
    }

}
