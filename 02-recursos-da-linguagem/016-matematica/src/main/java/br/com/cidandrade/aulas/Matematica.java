package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;
import java.math.BigDecimal;

/**
 *
 * @author cidandrade
 */
public class Matematica {

    public static void main(String[] args) {
        double a = -4.5, b = 7.0;
        double x = 0.1, y = 0.2;
        BigDecimal bX = new BigDecimal("0.1"),
                bY = new BigDecimal("0.2");
        escreveFormatado("Pi: %.4f", Math.PI);
        escreveFormatado("Absoluto de %.1f é %.1f",
                a, Math.abs(a));
        a = Math.abs(a);
        escreveFormatado("Arredonda 'a' para cima: %.1f",
                Math.ceil(a));
        escreveFormatado("Arredonda 'a' para baixo: %.1f",
                Math.floor(a));
        escreveFormatado("Logaritmo na base 10 de 'a': %.4f",
                Math.log10(a));
        escreveFormatado("Máximo de 'a' e 'b': %.1f",
                Math.max(a, b));
        escreveFormatado("Mínimo de 'a' e 'b': %.1f",
                Math.min(a, b));
        escreveFormatado("Quadrado de 'a': %.3f",
                Math.pow(a, 2));
        escreveFormatado("Raiz Quadrada de 'a': %.3f",
                Math.sqrt(a));
        escreveFormatado("Número pseudoaleatório: %.6f",
                Math.random());
        escreveFormatado("Número pseudoaleatório entre "
                + "15 e 40: %.6f", aleatorioEmFaixa(15, 40));
        escreveFormatado("Soma de x e y em double: %.18f", x + y);
        escreveFormatado("Soma de x e y em BigDecimal: %.18f",
                bX.add(bY));
        System.exit(0);
    }

    private static double aleatorioEmFaixa(double min, double max) {
        double tamanho = max - min;
        return Math.random() * tamanho + min;
    }

    private static void escreveFormatado(String mascara, double... doubles) {
        String escrever;
        switch (doubles.length) {
            case 1:
                escrever = String.format(mascara, doubles[0]);
                break;
            case 2:
                escrever = String.format(mascara, doubles[0], doubles[1]);
                break;
            default:
                escrever = "Erro na formatação";
        }
        Mensagem.mensagem(escrever);
    }

    private static void escreveFormatado(String mascara,
            BigDecimal... bigDecimals) {
        String escrever;
        switch (bigDecimals.length) {
            case 1:
                escrever = String.format(mascara, bigDecimals[0]);
                break;
            case 2:
                escrever = String.format(mascara, bigDecimals[0],
                        bigDecimals[1]);
                break;
            default:
                escrever = "Erro na formatação";
        }
        Mensagem.mensagem(escrever);
    }
}
