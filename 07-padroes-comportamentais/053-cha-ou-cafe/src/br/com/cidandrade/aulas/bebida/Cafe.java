package br.com.cidandrade.aulas.bebida;

public class Cafe extends Bebida {

    @Override
    public void adicionarIngrediente() {
        System.out.println("Adicionar canela");
    }

    @Override
    public void preparo() {
        System.out.println("Misturar pó com água");
    }

}
