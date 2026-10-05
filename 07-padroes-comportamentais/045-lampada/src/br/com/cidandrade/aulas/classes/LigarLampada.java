package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Comando;

// Comando concreto
public class LigarLampada implements Comando {

    Lampada lampada;

    public LigarLampada(Lampada lampada) {
        this.lampada = lampada;
    }

    @Override
    public void executar() {
        lampada.ligar();
    }

}
