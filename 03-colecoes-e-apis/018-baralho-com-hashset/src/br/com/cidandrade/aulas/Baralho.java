package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Baralho extends ConjuntoDeCartas {

    public Mao retiraCartas(int qtde) {
        Set<Carta> mao = new HashSet<>();
        Mao retorno = null;
        if (qtde <= conjunto.size()) {
            Iterator<Carta> i = conjunto.iterator();
            while (i.hasNext() && qtde > 0) {
                mao.add(i.next());
                qtde--;
            }
            retorno = new Mao(mao);
            conjunto.removeAll(mao);
        }
        return retorno;
    }

    public Baralho() {
        conjunto = new HashSet<>();
        for (Naipe naipe : Naipe.values()) {
            for (Face face : Face.values()) {
                conjunto.add(new Carta(face, naipe));
            }
        }
    }

}
