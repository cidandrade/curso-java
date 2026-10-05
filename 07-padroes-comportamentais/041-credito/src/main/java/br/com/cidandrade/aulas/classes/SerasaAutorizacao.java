package br.com.cidandrade.aulas.classes;

public class SerasaAutorizacao {

    private static final int PONTUACAO_MINIMA = 250;

    public static boolean autorizado(Requisicao requisicao) {
        Serasa serasa = Serasa.obterSerasa();
        String cpf = requisicao.getCliente().getCpf();
        return (serasa.pontuacaoPorCPF(cpf) > PONTUACAO_MINIMA);
    }
}
