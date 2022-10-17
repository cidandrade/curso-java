package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Venda;
import br.com.cidandrade.aulas.ifaces.FormasDesconto;
import br.com.cidandrade.util.Formatacao;
import br.com.cidandrade.util.Mensagem;

public class DescontoApp {

    public static void main(String[] args) {
        Venda venda = new Venda(1000.0);
        Mensagem.mensagem("R$ "
                + Formatacao.formDecimal(venda.cobrar()));
        venda.setFormadesconto(FormasDesconto.DescontoDeNatal());
        Mensagem.mensagem("R$ "
                + Formatacao.formDecimal(venda.cobrar()));
        System.exit(0);
    }

}
