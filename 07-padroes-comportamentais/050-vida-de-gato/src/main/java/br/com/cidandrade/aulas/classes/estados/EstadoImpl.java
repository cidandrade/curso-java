package br.com.cidandrade.aulas.classes.estados;

import br.com.cidandrade.aulas.classes.Gato;
import br.com.cidandrade.aulas.ifaces.Estado;

public abstract class EstadoImpl implements Estado {

    private final Gato gato;

    public EstadoImpl(Gato gato) {
        this.gato = gato;
    }

    @Override
    public abstract void aoEntrarNoEstado();

    @Override
    public abstract void obterEstado();

}
