/**
 * Classes que implementam o DAO da aplicação
 */
package br.com.cidandrade.aulas.modelo.dao;

import java.util.List;

/**
 * Interface genérica das classes DAO das entidades
 */
public interface DAO<T> {

    void inserir(T t);

    void alterar(T t);

    void remover(T t);

    List<T> selecionarTodos();
}
