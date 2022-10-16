package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Comando;

// Comando concreto
public class DesligarLampada implements Comando {

    Lampada lampada;

    public DesligarLampada(Lampada lampada) {
        this.lampada = lampada;
    }

    @Override
    public void executar() {
        lampada.desligar();
    }

}
