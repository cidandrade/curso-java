package br.com.cidandrade.aulas.bebida;

public class Cha extends Bebida {

    @Override
    public void adicionarIngrediente() {
        System.out.println("Acrescentar limão");
    }

    @Override
    public void preparo() {
        System.out.println("Colocar saquinho na água");
    }

}
