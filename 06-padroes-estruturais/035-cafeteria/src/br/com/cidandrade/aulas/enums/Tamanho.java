package br.com.cidandrade.aulas.enums;

public enum Tamanho {
    PEQUENO("Pequeno"), MEDIO("Médio"),
    GRANDE("Grande");
    private final String tamanho;

    private Tamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

}
