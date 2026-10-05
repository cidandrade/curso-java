package br.com.cidandrade.extras;

public class TrianguloApp {

    public static void main(String[] args) {
        testa("T1", 3, 4, -2);
        testa("T2", 3, 4, 8);
        testa("T3", 2, 2, 2);
        testa("T4", 2, 2, 2.5F);
        testa("T5", 2, 2.2F, 2.5F);
    }

    private static void testa(String id, float a, float b, float c) {
        Triangulo t = new Triangulo(a, b, c);
        System.out.println(id + ": " + t.tipoDeTriangulo());
    }

}
