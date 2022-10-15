package br.com.cidandrade.aulas.ifaces;

public interface Sujeito {

    public void registrarObservador(Observador o);

    public void removerObservador(Observador o);

    public void notificarObservadores();
}
