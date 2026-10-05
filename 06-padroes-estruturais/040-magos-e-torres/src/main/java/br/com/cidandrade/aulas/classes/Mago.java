package br.com.cidandrade.aulas.classes;

public class Mago {

    private final String nome;

    public Mago(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
