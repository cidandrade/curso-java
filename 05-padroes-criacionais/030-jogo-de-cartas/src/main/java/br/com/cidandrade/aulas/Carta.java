package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.enums.Face;
import br.com.cidandrade.aulas.enums.Naipe;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Carta {

    private Face face;
    private Naipe naipe;

    @Override
    public String toString() {
        return face.getFace() + " de " + naipe.getNaipe();
    }

}
