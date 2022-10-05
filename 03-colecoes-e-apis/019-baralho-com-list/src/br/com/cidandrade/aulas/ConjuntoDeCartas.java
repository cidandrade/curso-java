package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import br.com.cidandrade.aulas.interfaces.CartasManip;
import java.util.Collections;
import java.util.List;

public abstract class ConjuntoDeCartas implements CartasManip {

    List<Carta> conjunto;

    @Override
    public boolean contemCarta(Carta carta) {
        return conjunto.contains(carta);
    }

    @Override
    public boolean contemCarta(Face face, Naipe naipe) {
        return contemCarta(new Carta(face, naipe));
    }

    @Override
    public void removeCarta(Carta carta) {
        conjunto.remove(carta);
    }

    @Override
    public void removeCarta(Face face, Naipe naipe) {
        removeCarta(new Carta(face, naipe));
    }

    @Override
    public Carta cartaPorPosicao(int pos) {
        return conjunto.get(pos);
    }

    @Override
    public void ordena() {
        Collections.sort(conjunto);
    }

    @Override
    public void embaralha() {
        Collections.shuffle(conjunto);
    }

    @Override
    public int quantasCartas() {
        return conjunto.size();
    }

    @Override
    public void esvaziar() {
        conjunto.clear();
    }

    @Override
    public boolean estaVazio() {
        return conjunto.isEmpty();
    }

    @Override
    public void imprime() {
        ordena();
        conjunto.forEach(System.out::println);
    }

    public ConjuntoDeCartas() {
    }
}
