package br.com.cidandrade.aulas;

public class Triangulo extends FormaGeometrica {

    private double base, altura;

    @Override
    public double getArea() {
        return base * altura / 2;
    }

    @Override
    public String getDescricao() {
        return toString() + " / Área: " + FORMATADOR.format(getArea());
    }

    public Triangulo() {
        this.base = 0.0;
        this.altura = 0.0;
    }

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Triangulo other = (Triangulo) obj;
        if (Double.doubleToLongBits(this.base)
                != Double.doubleToLongBits(other.base)) {
            return false;
        }
        return Double.doubleToLongBits(this.altura)
                == Double.doubleToLongBits(other.altura);
    }

    @Override
    public String toString() {
        String baseFormatada = FORMATADOR.format(base);
        String alturaFormatada = FORMATADOR.format(altura);
        return "Triângulo com base de " + baseFormatada
                + " e altura de " + alturaFormatada;
    }

}
