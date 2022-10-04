package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class StringsApp {

    public static void main(String[] args) {
        String frase = "Hoje é um belo dia para aprender Java",
                outraFrase = "Amanhã também será", quatro = "4",
                numeros = "Uns números", fruta = "benene";
        int a = 1024;
        float b = 3.14F;
        String[] palavras;
        StringBuilder sb;

        Mensagem.mensagem("A 8a letra é " + frase.charAt(7));

        if (frase.compareTo(outraFrase) < 0) {
            Mensagem.mensagem("'" + frase
                    + "' vem antes na ordem alfabética");
        } else {
            Mensagem.mensagem("'" + outraFrase
                    + "' vem antes na ordem alfabética");
        }

        if (frase.contains("X")) {
            Mensagem.mensagem("Frase contém um 'X'");
        }

        if (frase.equals(outraFrase)) {
            Mensagem.mensagem("Frases são iguais");
        }

        Mensagem.mensagem(fruta.replace('e', 'a'));

        Mensagem.mensagem(frase.substring(10, 18));

        Mensagem.mensagem(frase.toUpperCase());

        Mensagem.mensagem(frase.toLowerCase());

        Mensagem.mensagem(String.valueOf(b)
                .replace('.', ','));

        Mensagem.mensagem(a + quatro);
        Mensagem.mensagem(quatro + a);
        Mensagem.mensagem(a + Integer.parseInt(quatro));

        Mensagem.mensagem(String.format("%s: %d e %.3f",
                numeros, a, b));

        Mensagem.mensagem(String.format(
                "\"%s\" tem %d letras", frase, frase.length()));

        Mensagem.mensagem(String.format(
                "Última posição de 'p': %d",
                frase.lastIndexOf('p')));

        palavras = frase.split(" ");
        sb = new StringBuilder();
        for (String palavra : palavras) {
            Mensagem.mensagem(palavra);
            sb.append(palavra).append(" ");
        }
        Mensagem.mensagem(sb);
        System.exit(0);
    }

}
