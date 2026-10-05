package br.com.cidandrade.aulas.ifaces;

public interface Questao {

    public void proxima();

    public void anterior();

    public void adicionar(String questao);

    public void remover(String questao);

    public String mostrar();

    public String mostrarTodas();
}
