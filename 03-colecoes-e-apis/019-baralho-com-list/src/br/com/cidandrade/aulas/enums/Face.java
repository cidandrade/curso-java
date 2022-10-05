package br.com.cidandrade.aulas.enums;

public enum Face {
    AS("Ás", 1), DOIS("Dois", 2),
    TRES("Três", 3), QUATRO("Quatro", 4),
    CINCO("Cinco", 5), SEIS("Seis", 6),
    SETE("Sete", 7), OITO("Oito", 8),
    NOVE("Nove", 9), DEZ("Dez", 10),
    J("Valete", 11), Q("Dama", 12),
    K("Rei", 13);
    private final String face;
    private final byte valor;

    private Face(String face, int valor) {
        this.face = face;
        this.valor = (byte) valor;
    }

    public String getFace() {
        return face;
    }

    public byte getValor() {
        return valor;
    }
}
