package br.com.cidandrade.aulas.classes;

import java.beans.PropertyChangeEvent;
import br.com.cidandrade.util.Mensagem;
import java.beans.PropertyChangeListener;

public class Dispositivo implements PropertyChangeListener{

    private float temperatura, umidade;

    public void exibir() {
        Mensagem.mensagem("Condições: " + temperatura + "ºC e "
                + umidade + "% de umidade");
    }

    public Dispositivo() {
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evento) {
        CjtoDados dados = (CjtoDados) evento.getNewValue();
        this.temperatura = dados.getTemperatura();
        this.umidade = dados.getUmidade();
        exibir();
    }
}
