package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.DadosMetereologicos;
import br.com.cidandrade.aulas.classes.Dispositivo;

public class Metereologia {

    public static void main(String[] args) {
        DadosMetereologicos dados = new DadosMetereologicos();
        Dispositivo dispositivo = new Dispositivo();
        
        dados.registrarObservador(dispositivo);

        dados.setMedidas(25, 65, 30.4F);
        dados.setMedidas(26, 70, 29.3F);
        dados.setMedidas(23, 90, 29.2F);
        
        System.exit(0);
    }

}
