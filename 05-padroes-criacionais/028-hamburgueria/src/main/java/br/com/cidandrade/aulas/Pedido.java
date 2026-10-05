package br.com.cidandrade.aulas;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    List<Hamburguer> itens = new ArrayList<>();
    String cliente;

    public Pedido com(Hamburguer hamburguer) {
        itens.add(hamburguer);
        return this;
    }

    public Pedido para(String cliente) {
        this.cliente = cliente;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("Pedido de %s\n", cliente));
        itens.forEach(h -> sb.append(h.toString()).append("\n"));
        return sb.toString();
    }

}
