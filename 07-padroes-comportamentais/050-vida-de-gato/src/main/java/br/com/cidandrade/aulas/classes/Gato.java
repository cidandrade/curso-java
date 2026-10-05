package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.classes.estados.Banhando;
import br.com.cidandrade.aulas.classes.estados.Brincando;
import br.com.cidandrade.aulas.classes.estados.Comendo;
import br.com.cidandrade.aulas.classes.estados.Dormindo;
import br.com.cidandrade.aulas.classes.estados.Faminto;
import br.com.cidandrade.aulas.ifaces.Estado;

public class Gato {

    private Estado estado;

    public Gato() {
        this.estado = new Dormindo(this);
    }

    public void tempoPassa() {
        if (estado.getClass().equals(Dormindo.class)) {
            mudarDeEstado(new Faminto(this));
        } else if (estado.getClass().equals(Faminto.class)) {
            mudarDeEstado(new Comendo(this));
        } else if (estado.getClass().equals(Comendo.class)) {
            mudarDeEstado(new Brincando(this));
        } else if (estado.getClass().equals(Brincando.class)) {
            mudarDeEstado(new Banhando(this));
        } else {
            mudarDeEstado(new Dormindo(this));
        }
    }

    public void obterEstado() {
        estado.obterEstado();
    }

    private void mudarDeEstado(Estado estado) {
        this.estado = estado;
        this.estado.aoEntrarNoEstado();
    }
}
