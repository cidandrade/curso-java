package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Elemento;
import br.com.cidandrade.aulas.ifaces.VisitanteCarrinhoIface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Fruta implements Elemento {

    private float precoPorKg, peso;
    private String nome;

    @Override
    public float aceitar(VisitanteCarrinhoIface visitante) {
        return visitante.visitar(this);
    }

}
