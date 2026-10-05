package br.com.cidandrade.aulas.classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Estacao {

    private final PropertyChangeSupport suporte;
    private float temperatura, umidade;

    public void aumentarTemperatura() {
        suporte.firePropertyChange("dados",
                new CjtoDados(this.temperatura,
                        this.umidade),
                new CjtoDados(this.temperatura + 1,
                        this.umidade));
        this.temperatura++;
    }

    public void diminuirTemperatura() {
        suporte.firePropertyChange("dados",
                new CjtoDados(this.temperatura,
                        this.umidade),
                new CjtoDados(this.temperatura - 1,
                        this.umidade));
        this.temperatura--;
    }

    public void aumentarUmidade() {
        if (this.umidade < 100) {
            suporte.firePropertyChange("dados",
                    new CjtoDados(this.temperatura,
                            this.umidade),
                    new CjtoDados(this.temperatura,
                            this.umidade + 1));
            this.umidade++;
        }
    }

    public void diminuirUmidade() {
        if (this.umidade > 0) {
            suporte.firePropertyChange("dados",
                    new CjtoDados(this.temperatura,
                            this.umidade),
                    new CjtoDados(this.temperatura,
                            this.umidade - 1));
            this.umidade--;
        }
    }

    public Estacao() {
        suporte = new PropertyChangeSupport(this);
        this.temperatura = 25;
        this.umidade = 60;
    }

    public void registrarObservador(PropertyChangeListener o) {
        suporte.addPropertyChangeListener(o);
    }

    public void removerObservador(PropertyChangeListener o) {
        suporte.removePropertyChangeListener(o);
    }

}
