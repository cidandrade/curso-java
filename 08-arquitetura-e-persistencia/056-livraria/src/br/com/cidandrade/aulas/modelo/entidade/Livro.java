package br.com.cidandrade.aulas.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a tabela Livro
 *
 * @author profandrade@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class Livro {

    private int id;
    private String nome, autor;
    private Categoria categoria;

}
