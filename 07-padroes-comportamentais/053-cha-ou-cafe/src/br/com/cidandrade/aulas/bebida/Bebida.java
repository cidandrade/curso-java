package br.com.cidandrade.aulas.bebida;

public abstract class Bebida {

    public void prepararBebida() {
        ferverAgua();
        preparo();
        servir();
        if (clienteDeseja()) {
            adicionarIngrediente();
        }
    }

    public void preparo() {
    }

    public void adicionarIngrediente() {
    }

    public void ferverAgua() {
        System.out.println("Fervendo água");
    }

    public void servir() {
        System.out.println("Colocar na xícara");
    }

    public boolean clienteDeseja() {
        return true;
    }

}
