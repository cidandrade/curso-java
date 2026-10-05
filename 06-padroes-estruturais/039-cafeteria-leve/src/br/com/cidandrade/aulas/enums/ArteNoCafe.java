package br.com.cidandrade.aulas.enums;

public enum ArteNoCafe {
    DISNEY("Disney"), COELHINHO("Coelhinho"),
    FRANCESA("Francesa"), DA_CASA("Estilo da casa"),
    ESPINHA("Espinha de peixe"), BUDA("Buda"),
    GATO("Gato");
    private final String nome;

    ArteNoCafe(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
