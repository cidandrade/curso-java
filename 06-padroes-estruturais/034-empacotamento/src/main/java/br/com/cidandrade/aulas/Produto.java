package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Preco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Produto implements Preco {

    private String nome;
    private float preco;

    @Override
    public float obtemPreco() {
        return preco;
    }

}
