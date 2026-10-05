package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.DesligarLampada;
import br.com.cidandrade.aulas.classes.Interruptor;
import br.com.cidandrade.aulas.classes.Lampada;
import br.com.cidandrade.aulas.classes.LigarLampada;
import br.com.cidandrade.aulas.ifaces.Comando;

public class LampadaApp {

    public static void main(String[] args) {
        Interruptor interruptor = new Interruptor();
        Lampada lampada = new Lampada();
        Comando acender = new LigarLampada(lampada);
        Comando apagar = new DesligarLampada(lampada);
        interruptor.setComando(acender);
        interruptor.aciona();
        interruptor.setComando(apagar);
        interruptor.aciona();
    }

}
