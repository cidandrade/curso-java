package br.com.cidandrade.aulas.fidelidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Regras do programa de fidelidade: 1 ponto por real inteiro gasto,
 * pontos em dobro às quartas-feiras e aviso de cupom ao chegar a 100
 * pontos. As três dependências chegam pelo construtor (injeção de
 * dependência), e nenhuma é criada aqui dentro
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class ProgramaFidelidade {

    public static final int PONTOS_PARA_CUPOM = 100;

    private final RepositorioClientes repositorio;
    private final Notificador notificador;
    private final Clock relogio;

    /**
     * @param repositorio onde os clientes ficam guardados
     * @param notificador como avisar o cliente
     * @param relogio de onde vem a data de hoje
     */
    public ProgramaFidelidade(RepositorioClientes repositorio,
            Notificador notificador, Clock relogio) {
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.relogio = relogio;
    }

    public Cliente cadastrar(String cpf, String nome) {
        Cliente cliente = new Cliente(cpf, nome);
        repositorio.salvar(cliente);
        return cliente;
    }

    /**
     * Credita os pontos de uma compra
     *
     * @param cpf CPF do cliente
     * @param valor valor da compra
     * @return pontos ganhos nesta compra
     * @throws IllegalArgumentException se o cliente não estiver cadastrado
     */
    public int registrarCompra(String cpf, BigDecimal valor) {
        Cliente cliente = repositorio.buscar(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não cadastrado: " + cpf));

        int ganhos = valor.setScale(0, RoundingMode.DOWN).intValueExact();
        if (LocalDate.now(relogio).getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            ganhos *= 2;
        }

        int antes = cliente.getPontos();
        cliente.adicionarPontos(ganhos);
        repositorio.salvar(cliente);

        if (antes < PONTOS_PARA_CUPOM && cliente.getPontos() >= PONTOS_PARA_CUPOM) {
            notificador.notificar(cliente, "Você chegou a " + cliente.getPontos()
                    + " pontos e ganhou um cupom!");
        }
        return ganhos;
    }
}
