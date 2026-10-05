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
                ensaio = new EventoUbatuba();
                break;
            case MASCULINO:
                ensaio = new MasculinoUbatuba();
                break;
            case FEMININO:
                ensaio = new FemininoUbatuba();
                break;
        }
        return ensaio;
    }

}
