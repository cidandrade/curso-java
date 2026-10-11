package reservas.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Sala de estudo: conhece número, capacidade e recursos, e diz se um grupo cabe nela. */
public final class Sala {

    private final int numero;
    private final int capacidade;
    private final Recursos recursos;

    public Sala(int numero, int capacidade, Recursos recursos) {
        if (capacidade < 1) {
            throw new RegraVioladaException(String.format(
                    "A capacidade da sala %d deve ser positiva; foi informado %d.", numero, capacidade));
        }
        this.numero = numero;
        this.capacidade = capacidade;
        this.recursos = Objects.requireNonNull(recursos, "Os recursos da sala são obrigatórios.");
    }

    public int numero() {
        return numero;
    }

    public int capacidade() {
        return capacidade;
    }

    public Recursos recursos() {
        return recursos;
    }

    public boolean comporta(int tamanhoGrupo) {
        return tamanhoGrupo <= capacidade;
    }

    @Override
    public boolean equals(Object outro) {
        return outro instanceof Sala sala && sala.numero == numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }

    @Override
    public String toString() {
        List<String> itens = new ArrayList<>();
        itens.add(capacidade + " lugares");
        if (recursos.projetor()) {
            itens.add("projetor");
        }
        if (recursos.lousaDigital()) {
            itens.add("lousa digital");
        }
        return "Sala " + numero + " (" + String.join(", ", itens) + ")";
    }
}
