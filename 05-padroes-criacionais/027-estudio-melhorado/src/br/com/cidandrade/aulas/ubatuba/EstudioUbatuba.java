package br.com.cidandrade.aulas.ubatuba;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.aulas.ensaio.Estudio;
import br.com.cidandrade.aulas.enums.TipoEnsaio;

public class EstudioUbatuba extends Estudio {

    @Override
    public Ensaio criaEnsaio(TipoEnsaio tipo) {
        Ensaio ensaio = null;
        switch (tipo) {
            case EVENTO:
                ensaio = new EventoUbatuba(new PreparativosUbatuba());
                break;
            case MASCULINO:
                ensaio = new MasculinoUbatuba(new PreparativosUbatuba());
                break;
            case FEMININO:
                ensaio = new FemininoUbatuba(new PreparativosUbatuba());
                break;
        }
        return ensaio;
    }

}
