package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Preco;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Caixa implements Preco {

    private final List<Preco> produtos = new ArrayList<>();

    public void adicionar(Preco preco) {
        produtos.add(preco);
    }

    @Override
    public float obtemPreco() {
        float preco = 0;
        for (Preco p : produtos) {
            preco += p.obtemPreco();
        }
        return preco;
    }

}
