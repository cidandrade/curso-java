package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.VisitanteCarrinhoIface;
import br.com.cidandrade.util.Mensagem;

public class VisitanteCarrinho implements VisitanteCarrinhoIface {

    @Override
    public float visitar(Livro livro) {
        float preco = livro.getPreco();
        if (livro.getPreco() > 50) {
            preco -= 5F;
        }
        Mensagem.mensagem("ISBN::" + livro.getIsbn()
                + " preço =" + preco);
        return preco;
    }

    @Override
    public float visitar(Fruta fruta) {
        float preco = fruta.getPeso() * fruta.getPrecoPorKg();
        Mensagem.mensagem(fruta.getNome() + " preço = " + preco);
        return preco;
    }

}
