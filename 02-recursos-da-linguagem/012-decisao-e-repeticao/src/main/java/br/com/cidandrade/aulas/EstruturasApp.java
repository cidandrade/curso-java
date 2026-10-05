package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

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
            Mensagem.mensagem("Teste é menor");
        } else if (teste > comparador) {
            Mensagem.mensagem("Teste é maior");
        } else {
            Mensagem.mensagem("São iguais");
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
        Mensagem.mensagem("Nota " + nota + " / Conceito " + conceito);

        contador = 1;
        while (contador <= 10) {
            Mensagem.mensagem(contador++);
        }

        contador = 1;
        do {
            Mensagem.mensagem(contador++);
        } while (contador <= 10);

        for (contador = 1; contador <= 10; contador++) {
            Mensagem.mensagem(contador);
        }

        contador = 1;
        for (;;) {
            Mensagem.mensagem(contador++);
            if (contador > 10) {
                break;
            }
        }

        for (byte valor : vetor) {
            Mensagem.mensagem(valor);
        }
        

        qtdeDeP = 0;
        for (contador = 0; contador < frase.length(); contador++) {
            if (frase.toLowerCase().charAt(contador) != 'p') {
                continue;
            }
            qtdeDeP++;
        }
        Mensagem.mensagem("Achei " + qtdeDeP + " letras 'p' na frase");
    }
}
