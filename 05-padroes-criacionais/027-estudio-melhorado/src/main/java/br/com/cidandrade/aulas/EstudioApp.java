package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ensaio.Ensaio;
import br.com.cidandrade.aulas.ensaio.Estudio;
import br.com.cidandrade.aulas.enums.TipoEnsaio;
import br.com.cidandrade.aulas.sp.EstudioSaoPaulo;

public class EstudioApp {

    public static void main(String[] args) {
        Estudio estudio = new EstudioSaoPaulo();
        Ensaio ensaio = estudio.contrataEnsaio(TipoEnsaio.CATALOGO);
        System.exit(0);
    }
}
