package br.com.cidandrade.aulas;

public class StringsApp {

    public static void main(String[] args) {
        String frase = "Hoje é um belo dia para aprender Java",
                outraFrase = "Amanhã também será", quatro = "4",
                numeros = "Uns números", fruta = "benene";
        int a = 1024;
        float b = 3.14F;
        String[] palavras;

        System.out.println("A 8a letra é " + frase.charAt(7));

        if (frase.compareTo(outraFrase) < 0) {
            System.out.println("'" + frase
                    + "' vem antes na ordem alfabética");
        } else {
            System.out.println("'" + outraFrase
                    + "' vem antes na ordem alfabética");
        }

        if (frase.contains("X")) {
            System.out.println("Frase contém um 'X'");
        }

        if (frase.equals(outraFrase)) {
            System.out.println("Frases são iguais");
        }

        System.out.println(fruta.replace('e', 'a'));

        System.out.println(frase.substring(10, 18));

        System.out.println(frase.toUpperCase());

        System.out.println(frase.toLowerCase());

        System.out.println(String.valueOf(b)
                .replace('.', ','));

        System.out.println(a + quatro);
        System.out.println(quatro + a);
        System.out.println(a + Integer.parseInt(quatro));

        System.out.println(String.format("%s: %d e %.3f",
                numeros, a, b));

        System.out.println(String.format(
                "\"%s\" tem %d letras", frase, frase.length()));

        System.out.println(String.format(
                "Última posição de 'p': %d",
                frase.lastIndexOf('p')));

        palavras = frase.split(" ");
        for (String palavra : palavras) {
            System.out.println(palavra);
        }
    }

}
