package br.com.cidandrade.aulas;

import java.util.Arrays;

/**
 *
 * @author cidandrade
 */
public class VetorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor;
        vetor = new int[10];
        vetor[0] = 100;
        vetor[1] = 200;
        vetor[2] = 300;
        vetor[3] = 400;
        vetor[4] = 500;
        vetor[5] = 600;
        vetor[6] = 700;
        vetor[7] = 800;
        vetor[8] = 900;
        vetor[9] = 1_000;
        System.out.println("5º elemento: " + vetor[4]);

        int[] outroVetor = {
            10, 20, 30,
            40, 50, 60,
            70, 80, 90,
            100};
        System.out.println("5º elemento do segundo vetor: " + outroVetor[4]);

        String[][] familiaReal = {
            {"Rei", "Rainha", "Príncipe", "Princesa"},
            {"João", "Maria", "Pedro"}
        };
        System.out.println(familiaReal[0][0] + " " + familiaReal[1][2]);
        System.out.println(familiaReal[0][3] + " " + familiaReal[1][1]);
        System.out.println(Arrays.toString(familiaReal[1]));
    }

}
