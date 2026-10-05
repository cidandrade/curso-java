package br.com.cidandrade.aulas.classes;

/**
 * Casas dos magos
 *
 * @author cidandrade
 */
public enum Casa {
    GRYFFINDOR("Gryffindor"),
    HUFFLEPUFF("Hufflepuff"),
    RAVENCLAW("Ravenclaw"),
    SLYTHERIN("Slytherin");
    private final String nome;

    private Casa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
