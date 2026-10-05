package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Baralho extends ConjuntoDeCartas {

    public Mao retiraCartas(int qtde) {
        List<Carta> mao = new ArrayList<>();
        Mao retorno = null;
        if (qtde <= conjunto.size()) {
            embaralha();
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
        conjunto = new ArrayList<>();
        for (Naipe naipe : Naipe.values()) {
            for (Face face : Face.values()) {
                conjunto.add(new Carta(face, naipe));
            }
        }
    }

}
