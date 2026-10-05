package br.com.cidandrade.aulas.enums;

public enum SaborDeCafe {
    CAPPUCCINO("Cappuccino"), ESPRESSO("Espresso"),
    FRAPPUCCINO("Frappuccino"), AFFOGATO("Affogato"),
    LATTE("Latte");
    private final String nome;

    SaborDeCafe(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return nome;
    }
}
