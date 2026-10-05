package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.CoR.ConcessaoCredito;
import br.com.cidandrade.aulas.classes.ListaDePessoas;
import br.com.cidandrade.aulas.classes.Pessoa;
import br.com.cidandrade.aulas.classes.Requisicao;
import br.com.cidandrade.aulas.classes.Serasa;
import br.com.cidandrade.aulas.enums.TipoDeCredito;
import br.com.cidandrade.util.Matematica;

public class CreditoApp {

    public static void main(String[] args) {
        Serasa serasa = Serasa.obterSerasa();
        for (int i = 0; i < 15; i++) {
            Pessoa pessoa = sortearPessoa();
            Requisicao req = new Requisicao(pessoa,
                    getTipo(pessoa), getValor());
            System.out.println(req);
            ConcessaoCredito concessao
                    = new ConcessaoCredito(req);
            concessao.avaliarConcessaoCredito();
        }
    }

    private static Pessoa sortearPessoa() {
        ListaDePessoas lista = ListaDePessoas.obterPessoas();
        return lista.getLista().get(
                (int) Matematica.aleatorioEmFaixa(
                        lista.getLista().size()));
    }

    private static TipoDeCredito getTipo(Pessoa pessoa) {
        TipoDeCredito tipo = TipoDeCredito.PESSOAL;
        if (pessoa.isTemImovel()) {
            tipo = TipoDeCredito.GARANTIA_IMOVEL;
        } else if (pessoa.isTemCarro()) {
            tipo = TipoDeCredito.GARANTIA_CARRO;
        } else if (Matematica.aleatorio() < 0.5) {
            tipo = TipoDeCredito.CONSIGNADO;
        }
        return tipo;
    }

    private static double getValor() {
        return 500 * (int) Matematica.aleatorioEmFaixa(1, 200);
    }
}
