package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;

public class Banhando extends EstadoImpl {

    private final Gato gato;

    public Banhando(Gato gato) {
        super(gato);
        this.gato = gato;
    }

    @Override
    public void aoEntrarNoEstado() {
        System.out.println("Quero parar");
    }

    @Override
    public void obterEstado() {
        System.out.println("Tomando banho");
    }

}
