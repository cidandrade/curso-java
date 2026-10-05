package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;

public class Dormindo extends EstadoImpl {

    private final Gato gato;

    public Dormindo(Gato gato) {
        super(gato);
        this.gato = gato;
    }

    @Override
    public void aoEntrarNoEstado() {
        System.out.println("Gato sonolento");
    }

    @Override
    public void obterEstado() {
        System.out.println("Gato dormindo");
    }

}
