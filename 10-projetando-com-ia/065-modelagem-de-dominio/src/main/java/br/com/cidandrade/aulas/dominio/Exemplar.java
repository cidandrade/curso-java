package br.com.cidandrade.aulas.dominio;

/**
 * Um volume físico de um Livro, identificado pelo código da etiqueta.
 * A biblioteca pode ter vários exemplares do mesmo livro, e cada um
 * está emprestado ou disponível
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Exemplar {

    private final String codigo;
    private final Livro livro;
    private Emprestimo emprestimoAtual;

    public Exemplar(String codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
    }

    public boolean isDisponivel() {
        return emprestimoAtual == null;
    }

    // Só o Emprestimo, no mesmo pacote, muda a situação do exemplar
    void emprestar(Emprestimo emprestimo) {
        if (!isDisponivel()) {
            throw new IllegalStateException("O exemplar " + codigo + " já está emprestado");
        }
        emprestimoAtual = emprestimo;
    }

    void devolver() {
        emprestimoAtual = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public Livro getLivro() {
        return livro;
    }

    @Override
    public String toString() {
        return livro + " [" + codigo + "]";
    }
}
