package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Categoria;
import br.com.cidandrade.aulas.modelo.entidade.Livraria;
import br.com.cidandrade.aulas.modelo.entidade.Livro;
import br.com.cidandrade.aulas.modelo.entidade.Preco;
import br.com.cidandrade.util.Entrada;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de implementação do Design Pattern Mediator Utilizada para desacoplar
 * cada DAO das entidades das outras classes DAO
 */
public class Mediator implements DAOMediator {

    /**
     * Retorna um objeto Categoria, dado o campo id da mesma
     *
     * @param id int
     * @return Categoria
     */
    @Override
    public Categoria getCategoriaPorId(int id) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.getCategoriaPorId(id);
    }

    /**
     * Retorna um objeto Categoria, dado o campo nome da mesma
     *
     * @param nome String
     * @return Categoria
     */
    @Override
    public Categoria getCategoriaPorNome(String nome) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.getCategoriaPorNome(nome);
    }

    /**
     * Retorna um objeto Livraria, dado o campo id da mesma
     *
     * @param id int
     * @return Livraria
     */
    @Override
    public Livraria getLivrariaPorId(int id) {
        LivrariaDAO dao = new LivrariaDAO();
        return dao.getLivrariaPorId(id);
    }

    /**
     * Retorna um objeto Livro, dado o campo id da mesma
     *
     * @param id int
     * @return Livro
     */
    @Override
    public Livro getLivroPorId(int id) {
        LivroDAO dao = new LivroDAO();
        return dao.getLivroPorId(id);
    }

    /**
     * Apaga todos os preços relacionados a determinado livro
     *
     * @param livro Livro
     */
    @Override
    public void removerPorLivro(Livro livro) {
        PrecoDAO dao = new PrecoDAO();
        dao.removerPorLivro(livro);
    }

    /**
     * Insere preços de cada livro nas diferentes livrarias
     *
     * @param nome String
     */
    @Override
    public void inserirPrecos(String nome) {
        LivroDAO lDAO = new LivroDAO();
        LivrariaDAO lvDAO = new LivrariaDAO();
        PrecoDAO pDAO = new PrecoDAO();
        Livro livro = lDAO.getLivroPorNome(nome);
        List<Livraria> livrarias = lvDAO.selecionarTodos();

        for (Livraria l : livrarias) {
            float preco = Entrada.getFloat("Preço do " + nome
                    + " na " + l.getNome() + "?");
            pDAO.inserir(new Preco(livro, l,
                    LocalDate.now(), preco));
        }
    }

}
