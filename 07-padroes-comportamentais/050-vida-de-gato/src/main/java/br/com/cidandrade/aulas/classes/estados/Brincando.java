package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;

public class Brincando extends EstadoImpl {

    private final Gato gato;

    public Brincando(Gato gato) {
        super(gato);
        this.gato = gato;
    }

    @Override
    public void aoEntrarNoEstado() {
        System.out.println("Quero brincar");
    }

    @Override
    public void obterEstado() {
        System.out.println("Correndo pela casa com os brinquedos");
    }

}
