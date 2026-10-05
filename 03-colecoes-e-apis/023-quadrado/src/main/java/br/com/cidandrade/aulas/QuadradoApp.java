package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Entrada;
import br.com.cidandrade.util.Mensagem;

public class QuadradoApp {

    public static void main(String[] args) {
        int numero;
        numero = Entrada.getInteger("Digite um número");
        Quadrado quadrado = new Quadrado(numero);
        numero = (int) quadrado.eleva();
        Mensagem.mensagem("O quadrado é "+numero);
    }

}
