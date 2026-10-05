package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Gato;

public class VidaDeGatoApp {

    public static void main(String[] args) {
        Gato gato = new Gato();
        gato.obterEstado();
        for (int i = 0; i < 5; i++) {
            gato.tempoPassa();
            gato.obterEstado();
        }
    }

}
