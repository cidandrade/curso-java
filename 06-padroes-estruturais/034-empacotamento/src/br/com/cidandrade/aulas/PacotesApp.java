package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class PacotesApp {

    public static void main(String[] args) {
        Caixa cxCelular, cxFone, cxTelefonia, cxMartelo, cxPedido;
        Produto celular, carregador, fone, martelo;

        cxCelular = new Caixa();
        celular = new Produto("Celular", 3_500F);
        carregador = new Produto("Carregador", 60F);
        cxCelular.adicionar(celular);
        cxCelular.adicionar(carregador);

        cxFone = new Caixa();
        fone = new Produto("Fone de ouvido", 25F);
        cxFone.adicionar(fone);

        cxTelefonia = new Caixa();
        cxTelefonia.adicionar(cxCelular);
        cxTelefonia.adicionar(cxFone);

        cxMartelo = new Caixa();
        martelo = new Produto("Martelo", 36F);
        cxMartelo.adicionar(martelo);

        cxPedido = new Caixa();
        cxPedido.adicionar(cxMartelo);
        cxPedido.adicionar(cxTelefonia);

        Mensagem.mensagem(String.format(
                "Preço do pedido: R$ %.2f",
                cxPedido.obtemPreco()));
    }

}
