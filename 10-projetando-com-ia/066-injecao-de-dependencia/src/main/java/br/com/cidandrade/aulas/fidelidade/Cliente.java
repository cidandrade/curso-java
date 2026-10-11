package br.com.cidandrade.aulas.fidelidade;

/**
 * Cliente do programa de fidelidade
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Cliente {

    private final String cpf;
    private final String nome;
    private int pontos;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    void adicionarPontos(int quantidade) {
        pontos += quantidade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }
}
