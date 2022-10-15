package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Observador;
import br.com.cidandrade.aulas.ifaces.Sujeito;
import java.util.ArrayList;
import java.util.List;

public class DadosMetereologicos implements Sujeito {

    private final List<Observador> observadores;
    private float temperatura, umidade, pressao;

    public void medidasAlteradas() {
        notificarObservadores();
    }

    public void setMedidas(float temperatura, float umidade, float pressao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        medidasAlteradas();
    }

    public DadosMetereologicos() {
        observadores = new ArrayList<>();
    }

    @Override
    public void registrarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void removerObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        observadores.forEach(o -> o.atualizar(temperatura, umidade, pressao));
    }

}
