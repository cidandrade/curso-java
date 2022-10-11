package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class ParaisoApp {

    public static void main(String[] args) {
        Pedido p = new Pedido()
                .para("Cid")
                .com(new Hamburguer.Construtor(
                        (byte) 2, Hamburguer.TamanhoCarne.M)
                        .queijoPrato()
                        .bacon()
                        .tomate()
                        .construir())
                .com(new Hamburguer.Construtor(
                        (byte) 1, Hamburguer.TamanhoCarne.P)
                        .queijoBrie()
                        .agriao()
                        .rucula()
                        .ovo()
                        .construir());
        Mensagem.mensagem(p);
        System.exit(0);
    }

}
