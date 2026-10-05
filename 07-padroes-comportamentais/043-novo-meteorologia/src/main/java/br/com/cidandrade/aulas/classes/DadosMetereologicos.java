package br.com.cidandrade.aulas.classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DadosMetereologicos { 

    private final PropertyChangeSupport suporte;
    private float temperatura, umidade, pressao;

    public void setMedidas(float temperatura, float umidade, float pressao) {
        suporte.firePropertyChange("dados",
                new CjtoDados(this.temperatura,
                        this.umidade, this.pressao),
                new CjtoDados(temperatura, umidade, pressao));
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
    }

    public DadosMetereologicos() {
        suporte = new PropertyChangeSupport(this);
    }

    public void registrarObservador(PropertyChangeListener o) {
        suporte.addPropertyChangeListener(o);
    }

    public void removerObservador(PropertyChangeListener o) {
        suporte.removePropertyChangeListener(o);
    }

}
