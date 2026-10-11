package br.com.cidandrade.aulas.dominio;

import java.util.Objects;

/**
 * A obra: título, autor e ISBN. Não é algo que se empresta; o que se
 * empresta é um Exemplar dela. Dois objetos com o mesmo ISBN são o
 * mesmo livro
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public final class Livro {

    private final String isbn;
    private final String titulo;
    private final String autor;

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = Objects.requireNonNull(isbn);
        this.titulo = Objects.requireNonNull(titulo);
        this.autor = Objects.requireNonNull(autor);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Livro outro && isbn.equals(outro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return titulo + " (" + autor + ")";
    }
}
