package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Categoria;
import br.com.cidandrade.aulas.modelo.entidade.Livraria;
import br.com.cidandrade.aulas.modelo.entidade.Livro;

/**
 * Interface para classe do Design Pattern Mediator
 */
public interface DAOMediator {

    Categoria getCategoriaPorId(int id);

    Categoria getCategoriaPorNome(String nome);

    Livraria getLivrariaPorId(int id);

    Livro getLivroPorId(int id);

    void removerPorLivro(Livro livro);

    void inserirPrecos(String nome);

}
