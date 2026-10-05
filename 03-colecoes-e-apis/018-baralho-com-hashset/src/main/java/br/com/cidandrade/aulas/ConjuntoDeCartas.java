package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import br.com.cidandrade.aulas.interfaces.CartasManip;
import java.util.Set;

public abstract class ConjuntoDeCartas implements CartasManip {

    Set<Carta> conjunto;

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
        conjunto.forEach(System.out::println);
    }

    public ConjuntoDeCartas() {
    }
}
