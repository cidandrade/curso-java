package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Mensagem;

public class Vida {

    private String tempo;

    public void setTempo(String tempo) {
        Mensagem.mensagem("Definindo tempo para " + tempo);
        this.tempo = tempo;
    }

    public Memento salvarMemento() {
        Mensagem.mensagem("Salvando");
        return new Memento(tempo);
    }

    public void restaurar(Memento memento) {
        tempo = memento.getTempo();
        Mensagem.mensagem("Restaurado para " + tempo);
    }

    public static class Memento {

        private final String tempo;

        public Memento(String tempo) {
            this.tempo = tempo;
        }

        public String getTempo() {
            return tempo;
        }
    }
}
