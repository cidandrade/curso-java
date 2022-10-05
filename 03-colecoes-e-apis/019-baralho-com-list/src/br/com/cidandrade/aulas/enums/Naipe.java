package br.com.cidandrade.aulas.enums;

public enum Naipe {
    COPAS("Copas", 3),
    OUROS("Ouros", 1),
    PAUS("Paus", 0),
    ESPADAS("Espadas", 2);
    private final String naipe;
    private final byte valor;

    private Naipe(String naipe, int valor) {
        this.naipe = naipe;
        this.valor = (byte) valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public byte getValor() {
        return valor;
    }
}
