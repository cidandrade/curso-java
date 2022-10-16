package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.IteratorIface;
import lombok.Getter;

@Getter
public class Redondas implements IteratorIface {

    private final ItemDeMenu[] itens;
    private int posicao;

    public Redondas() {
        itens = new ItemDeMenu[3];
        itens[0] = new ItemDeMenu("Muçarela",
                "Pizza de muçarela",
                true, 40F);
        itens[1] = new ItemDeMenu("Europa",
                "Pizza de muçarela, presunto e champignon",
                false, 48F);
        itens[2] = new ItemDeMenu("Caipira",
                "Pizza de frango, milho e palmito",
                true, 46F);
        posicao = 0;
    }

    @Override
    public boolean hasNext() {
        return (posicao < itens.length);
    }

    @Override
    public ItemDeMenu next() {
        ItemDeMenu item = itens[posicao];
        posicao++;
        return item;
    }
}
