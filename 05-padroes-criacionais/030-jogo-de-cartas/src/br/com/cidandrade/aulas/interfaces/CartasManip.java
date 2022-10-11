package br.com.cidandrade.aulas.interfaces;

import br.com.cidandrade.aulas.Carta;
import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;

public interface CartasManip {

    public boolean contemCarta(Carta carta);

    public boolean contemCarta(Face face, Naipe naipe);

    public void removeCarta(Carta carta);

    public void removeCarta(Face face, Naipe naipe);

    public int quantasCartas();

    public void esvaziar();

    public boolean estaVazio();

    public void imprime();
}
