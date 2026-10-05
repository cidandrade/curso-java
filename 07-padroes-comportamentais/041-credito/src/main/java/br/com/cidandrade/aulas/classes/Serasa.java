package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Matematica;
import java.util.HashMap;
import java.util.Map;

public class Serasa {

    private static Serasa serasa;
    private final Map<String, PessoaSerasa> lista;

    // Singleton
    public static Serasa obterSerasa() {
        if (serasa == null) {
            serasa = new Serasa();
        }
        return serasa;
    }

    private Serasa() {
        lista = new HashMap<>();
        ListaDePessoas pessoas = ListaDePessoas.obterPessoas();
        for (Pessoa p : pessoas.getLista()) {
            lista.put(p.getCpf(), new PessoaSerasa(p));
        }
    }

    public int pontuacaoPorCPF(String cpf) {
        return lista.get(cpf).getPontuacao();
    }

    private class PessoaSerasa {

        private final Pessoa pessoa;
        private final int pontuacao;

        public PessoaSerasa(Pessoa pessoa) {
            this.pessoa = pessoa;
            this.pontuacao = (int) Matematica.aleatorioEmFaixa(1_000);
        }

        public Pessoa getPessoa() {
            return pessoa;
        }

        public int getPontuacao() {
            return pontuacao;
        }
    }
}
