package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Vida;
import java.util.ArrayList;
import java.util.List;

public class VidaLongaApp {

    public static void main(String[] args) {
        List<Vida.Memento> salvos = new ArrayList<>();
        Vida vida = new Vida();

        vida.setTempo("1000 a.C.");
        salvos.add(vida.salvarMemento());
        vida.setTempo("1000 d.C.");
        salvos.add(vida.salvarMemento());
        vida.setTempo("2000 d.C.");
        salvos.add(vida.salvarMemento());
        vida.setTempo("4000 a.C.");
        salvos.add(vida.salvarMemento());
        vida.restaurar(salvos.get(0));
        
        System.exit(0);
    }

}
