package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Carta implements Comparable<Carta> {

    private Face face;
    private Naipe naipe;

    @Override
    public int hashCode() {
        return naipe.getValor() * 13 + face.getValor();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.face != other.face) {
            return false;
        }
        return this.naipe == other.naipe;
    }

    @Override
    public String toString() {
        return face.getFace() + " de " + naipe.getNaipe();
    }

    @Override
    public int compareTo(Carta outraCarta) {
        return hashCode() - outraCarta.hashCode();
    }

}
