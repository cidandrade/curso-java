package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;

public class Faminto extends EstadoImpl {

    private final Gato gato;

    public Faminto(Gato gato) {
        super(gato);
        this.gato = gato;
    }

    @Override
    public void aoEntrarNoEstado() {
        System.out.println("Ficando irritado");
    }

    @Override
    public void obterEstado() {
        System.out.println("Batendo em tudo, rasgando a cortina");
    }

}
