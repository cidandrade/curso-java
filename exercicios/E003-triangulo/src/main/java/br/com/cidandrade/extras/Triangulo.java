package br.com.cidandrade.extras;

public class Triangulo {

    private final float lado1, lado2, lado3;

    public Triangulo(float lado1, float lado2, float lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public String tipoDeTriangulo() {
        String tipo = "Não é triângulo";
        if (testaTriangulo()) {
            if (lado1 == lado2 && lado2 == lado3) {
                tipo = "Equilátero";
            } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
                tipo = "Isósceles";
            } else {
                tipo = "Escaleno";
            }
        }
        return tipo;
    }

    private boolean testaTriangulo() {
        boolean teste = (lado1 > 0F && lado2 > 0F && lado3 > 0F);
        if (teste) {
            return (((lado1 + lado2) > lado3)
                    && ((lado1 + lado3) > lado2)
                    && ((lado2 + lado3) > lado1));
        } else {
            return false;
        }
    }

}
