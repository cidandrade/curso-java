package br.com.cidandrade.aulas;

/**
 * Estruturas de decisão e repetição
 *
 * @author cidandrade
 */
public class EstruturasApp {

    public static void main(String[] args) {
        byte teste = 10, comparador = 5, nota, contador, qtdeDeP;
        byte[] vetor = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char conceito;
        String frase = "Pimenta preta no picles é a preferida do Pedro";

        if (teste < comparador) {
            System.out.println("Teste é menor");
        } else if (teste > comparador) {
            System.out.println("Teste é maior");
        } else {
            System.out.println("São iguais");
        }

        nota = (teste < comparador ? (byte) 3 : (byte) 7);

        switch (nota) {
            case 10:
                conceito = 'A';
                break;
            case 9:
            case 8:
                conceito = 'B';
                break;
            case 7:
            case 6:
            case 5:
                conceito = 'C';
                break;
            case 4:
            case 3:
            case 2:
                conceito = 'D';
                break;
            case 1:
            case 0:
                conceito = 'E';
                break;
            default:
                conceito = '?';
        }
        System.out.println("Nota " + nota + " / Conceito " + conceito);

        contador = 1;
        while (contador <= 10) {
            System.out.println(contador++);
        }

        contador = 1;
        do {
            System.out.println(contador++);
        } while (contador <= 10);

        for (contador = 1; contador <= 10; contador++) {
            System.out.println(contador);
        }

        contador = 1;
        for (;;) {
            System.out.println(contador++);
            if (contador > 10) {
                break;
            }
        }

        for (byte valor : vetor) {
            System.out.println(valor);
        }

        qtdeDeP = 0;
        for (contador = 0; contador < frase.length(); contador++) {
            if (frase.toLowerCase().charAt(contador) != 'p') {
                continue;
            }
            qtdeDeP++;
        }
        System.out.println("Achei " + qtdeDeP + " letras 'p' na frase");
    }
}
