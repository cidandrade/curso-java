package br.com.cidandrade.util;

public class Matematica {

    public static double aleatorio(){
        return Math.random();
    }
    
    public static double aleatorioEmFaixa(double max) {
        return aleatorioEmFaixa(0, max);
    }

    public static double aleatorioEmFaixa(double min, double max) {
        double tamanho = max - min;
        return Math.random() * tamanho + min;
    }
}
