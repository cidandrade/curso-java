package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.IteratorIface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;

@Getter
public class HotHouse implements IteratorIface {

    List<ItemDeMenu> itens;
    Iterator<ItemDeMenu> iterator;

    public HotHouse() {
        itens = new ArrayList<>();
        adiciona("Hotdog especial",
                "Hot Dog com uma salsicha e mostarda",
                false, 12F);
        adiciona("Da Casa",
                "Duas salsichas, purê, mostarda e batata palha",
                false, 18.5F);
        adiciona("Vegetariano",
                "Salsicha de soja e maionese vegana",
                false, 25F);
        iterator = itens.iterator();
    }

    private void adiciona(String nome, String descricao,
            boolean vegetariano, float preco) {
        itens.add(new ItemDeMenu(nome, descricao, vegetariano, preco));
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ItemDeMenu next() {
        return iterator.next();
    }
}
