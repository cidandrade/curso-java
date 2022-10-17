package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Fruta;
import br.com.cidandrade.aulas.classes.Livro;
import br.com.cidandrade.aulas.classes.VisitanteCarrinho;
import br.com.cidandrade.aulas.ifaces.Elemento;
import br.com.cidandrade.aulas.ifaces.VisitanteCarrinhoIface;
import br.com.cidandrade.util.Mensagem;

public class CarrinhoApp {

    public static void main(String[] args) {
        Elemento[] itens = {
            new Livro(20, "1234"),
            new Livro(100, "5678"),
            new Fruta(10, 2, "Banana"),
            new Fruta(5, 5, "Maça")};

        float total = calcularPreco(itens);
        Mensagem.mensagem("Preço Total = " + total);
    }

    private static float calcularPreco(Elemento[] itens) {
        VisitanteCarrinhoIface visitante = new VisitanteCarrinho();
        float soma = 0;
        for (Elemento item : itens) {
            soma = soma + item.aceitar(visitante);
        }
        return soma;
    }
}
