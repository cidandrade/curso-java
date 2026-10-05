package br.com.cidandrade.aulas.classes;

// Receiver
import br.com.cidandrade.util.Mensagem;

public class Lampada {

    private boolean ligado;

    public void ligar() {
        ligado = true;
        Mensagem.mensagem("Lâmpada foi acesa");
    }

    public void desligar() {
        ligado = false;
        Mensagem.mensagem("Lâmpada foi apagada");
    }
}
