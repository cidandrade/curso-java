package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;

public class Comendo extends EstadoImpl {

    private final Gato gato;

    public Comendo(Gato gato) {
        super(gato);
        this.gato = gato;
    }

    @Override
    public void aoEntrarNoEstado() {
        System.out.println("Acalmando");
    }

    @Override
    public void obterEstado() {
        System.out.println("No pote, nem fale com ele");
    }

}
