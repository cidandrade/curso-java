package br.com.cidandrade.aulas.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sócio da biblioteca. É ele quem pega livros emprestados, então as regras
 * de quem pode pegar ficam aqui, e não numa classe "serviço" separada
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Socio {

    public static final int LIMITE_DE_EMPRESTIMOS = 3;

    private final String nome;
    private final List<Emprestimo> ativos = new ArrayList<>();

    public Socio(String nome) {
        this.nome = nome;
    }

    /**
     * @param hoje data de referência
     * @return se o sócio está abaixo do limite e sem empréstimo atrasado
     */
    public boolean podeEmprestar(LocalDate hoje) {
        return ativos.size() < LIMITE_DE_EMPRESTIMOS
                && ativos.stream().noneMatch(e -> e.isAtrasado(hoje));
    }

    /**
     * Pega um exemplar emprestado
     *
     * @param exemplar exemplar desejado
     * @param hoje data do empréstimo
     * @return o empréstimo criado
     * @throws IllegalStateException se o sócio não puder emprestar ou o
     * exemplar não estiver disponível
     */
    public Emprestimo emprestar(Exemplar exemplar, LocalDate hoje) {
        if (!podeEmprestar(hoje)) {
            throw new IllegalStateException(
                    nome + " atingiu o limite ou tem empréstimo atrasado");
        }
        Emprestimo emprestimo = new Emprestimo(this, exemplar, hoje);
        ativos.add(emprestimo);
        return emprestimo;
    }

    void encerrar(Emprestimo emprestimo) {
        ativos.remove(emprestimo);
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return Collections.unmodifiableList(ativos);
    }

    public String getNome() {
        return nome;
    }
}
