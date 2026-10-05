package br.com.cidandrade.aulas;

public class Quadrado<T extends Number> {

    private final T num;

    public Quadrado(T num) {
        this.num = num;
    }

    public double eleva() {
        return num.doubleValue() * num.doubleValue();
    }

}
