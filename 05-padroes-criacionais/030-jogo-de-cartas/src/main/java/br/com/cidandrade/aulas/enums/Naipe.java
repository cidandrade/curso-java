package br.com.cidandrade.aulas.enums;

public enum Naipe {
    COPAS("Copas"), 
    OUROS("Ouros"), 
    PAUS("Paus"), 
    ESPADAS("Espadas");
    private final String naipe;

    private Naipe(String naipe) {
        this.naipe = naipe;
    }

    public String getNaipe() {
        return naipe;
    }
}
