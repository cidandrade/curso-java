package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import br.com.cidandrade.aulas.interfaces.CartasManip;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Baralho implements CartasManip {

    Set<Carta> baralho;

    @Override
    public boolean contemCarta(Carta carta) {
        return baralho.contains(carta);
    }

    @Override
    public boolean contemCarta(Face face, Naipe naipe) {
        return contemCarta(new Carta(face, naipe));
    }

    @Override
    public void removeCarta(Carta carta) {
        baralho.remove(carta);
    }

    @Override
    public void removeCarta(Face face, Naipe naipe) {
        removeCarta(new Carta(face, naipe));
    }

    @Override
    public int quantasCartas() {
        return baralho.size();
    }

    @Override
    public void esvaziar() {
        baralho.clear();
    }

    @Override
    public boolean estaVazio() {
        return baralho.isEmpty();
    }

    @Override
    public void imprime() {
        baralho.forEach(System.out::println);
    }

    public Mao retiraCartas(int qtde) {
        Set<Carta> mao = new HashSet<>();
        Mao retorno = null;
        if (qtde <= baralho.size()) {
            Iterator<Carta> i = baralho.iterator();
            while (i.hasNext() && qtde > 0) {
                mao.add(i.next());
                qtde--;
            }
            retorno = new Mao(mao);
            baralho.removeAll(mao);
        }
        return retorno;
    }

    public Baralho() {
        baralho = new HashSet<>();
        for (Naipe naipe : Naipe.values()) {
            for (Face face : Face.values()) {
                baralho.add(new Carta(face, naipe));
            }
        }
    }
}
