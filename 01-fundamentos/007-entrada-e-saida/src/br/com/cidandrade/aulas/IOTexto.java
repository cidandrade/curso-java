package br.com.cidandrade.aulas;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cidandrade
 */
public class IOTexto {

    public static void modoTexto() {
        String nome;
        byte idade;
        float altura;

        // Try-with-resources
        try ( Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite seu nome: ");
            nome = scanner.nextLine();
            if (!nome.equals("")) {
                System.out.println("Qual é sua idade, " + nome + "?");
                try {
                    idade = scanner.nextByte();
                    System.out.println(idade + " anos é uma boa idade.");
                    System.out.println("Qual é a sua altura?");
                    altura = scanner.nextFloat();
                    System.out.println(String.format(
                            "%.2f m de altura!!! Legal, %s",
                            altura, nome));
                } catch (InputMismatchException e) {
                    System.out.println("Tive um problema para "
                            + "entender isso");
                } finally {
                    System.out.println("Até mais!!!");
                }
            } else {
                System.out.println("Não quer conversar? Tudo bem!");
            }
        }
    }
}
