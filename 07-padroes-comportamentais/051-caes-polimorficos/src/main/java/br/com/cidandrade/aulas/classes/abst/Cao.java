package br.com.cidandrade.aulas.classes.abst;

import br.com.cidandrade.aulas.ifaces.ComportDieta;
import br.com.cidandrade.aulas.ifaces.ComportLatido;
import lombok.Setter;

public abstract class Cao {

    @Setter
    protected ComportDieta dieta;
    @Setter
    protected ComportLatido latido;

    public Cao() {
    }

    public void latir() {
        latido.latir();
    }

    public void comer() {
        dieta.comer();
    }
}
