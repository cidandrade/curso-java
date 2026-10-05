package br.com.cidandrade.aulas.enums;

public enum Face {
    AS("Ás"), DOIS("Dois"), TRES("Três"),
    QUATRO("Quatro"), CINCO("Cinco"),
    SEIS("Seis"), SETE("Sete"),
    OITO("Oito"), NOVE("Nove"),
    DEZ("Dez"), J("Valete"), 
    Q("Dama"), K("Rei");
    private String face;

    private Face(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }
}
