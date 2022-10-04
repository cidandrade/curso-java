package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import br.com.cidandrade.aulas.interfaces.CartasManip;
import br.com.cidandrade.util.Mensagem;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mao implements CartasManip {

    Set<Carta> mao;

    @Override
    public boolean contemCarta(Carta carta) {
        return mao.contains(carta);
    }

    @Override
    public boolean contemCarta(Face face, Naipe naipe) {
        return contemCarta(new Carta(face, naipe));
    }

    @Override
    public void removeCarta(Carta carta) {
        mao.remove(carta);
    }

    @Override
    public void removeCarta(Face face, Naipe naipe) {
        removeCarta(new Carta(face, naipe));
    }

    @Override
    public int quantasCartas() {
        return mao.size();
    }

    @Override
    public void esvaziar() {
        mao.clear();
    }

    @Override
    public boolean estaVazio() {
        return mao.isEmpty();
    }

    @Override
    public void imprime() {
        StringBuilder sb = new StringBuilder();
        mao.forEach((c) -> sb.append(c).append(", "));
        String listaMao = sb.substring(0, sb.length() - 2);
        Mensagem.mensagem(listaMao);
    }

}
