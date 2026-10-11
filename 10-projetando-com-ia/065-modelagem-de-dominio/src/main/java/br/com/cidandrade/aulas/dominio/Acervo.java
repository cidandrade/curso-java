package br.com.cidandrade.aulas.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Os exemplares da biblioteca. Responde a perguntas sobre o conjunto,
 * como "quantos exemplares deste livro estão disponíveis?"
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Acervo {

    private final List<Exemplar> exemplares = new ArrayList<>();

    public Exemplar adicionar(Livro livro, String codigo) {
        Exemplar exemplar = new Exemplar(codigo, livro);
        exemplares.add(exemplar);
        return exemplar;
    }

    public List<Exemplar> disponiveis(Livro livro) {
        return exemplares.stream()
                .filter(e -> e.getLivro().equals(livro) && e.isDisponivel())
                .toList();
    }
}
