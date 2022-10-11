package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class ParaisoApp {

    public static void main(String[] args) {
        Hamburguer h1 = new Hamburguer.Construtor(
                (byte) 2, Hamburguer.TamanhoCarne.M)
                .queijoPrato()
                .bacon()
                .tomate()
                .construir();
        Hamburguer h2 = new Hamburguer.Construtor(
                (byte) 1, Hamburguer.TamanhoCarne.P)
                .queijoBrie()
                .agriao()
                .rucula()
                .ovo()
                .construir();
        Hamburguer h3 = h2.clonagem();
        Pedido p = new Pedido()
                .para("Cid")
                .com(h1)
                .com(h2)
                .com(h3);
        Mensagem.mensagem(p);
        System.exit(0);
    }

}
