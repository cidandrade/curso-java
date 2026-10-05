package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.classes.abst.Cao;
import br.com.cidandrade.aulas.classes.comport.DietaNormal;
import br.com.cidandrade.aulas.classes.comport.LatidoBrincalhao;

public class Labrador extends Cao {

    public Labrador() {
        dieta = new DietaNormal();
        latido = new LatidoBrincalhao();
    }

}
