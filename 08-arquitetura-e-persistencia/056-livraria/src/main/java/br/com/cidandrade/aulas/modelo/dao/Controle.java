package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.BD;
import br.com.cidandrade.aulas.modelo.entidade.Categoria;
import br.com.cidandrade.aulas.modelo.entidade.Preco;
import br.com.cidandrade.util.Formatacao;
import br.com.cidandrade.util.Mensagem;
import java.util.List;

/**
 * Classe Controle para acesso ao Modelo
 *
 * @author profandrade@gmail.com
 */
public class Controle {

    /**
     * Alterar livro
     *
     * @param idLivro int
     * @param nome String
     * @param autor String
     * @param categoria String
     */
    public void alterarLivro(int idLivro, String nome,
            String autor, String categoria) {
        LivroDAO dao = new LivroDAO();
        if (nome.length() <= 30 && autor.length() <= 25) {
            dao.alterar(idLivro, nome, autor, categoria);
        } else {
            Mensagem.msgErro("Nome do livro ou do autor longos demais");
        }
    }

    /**
     * Retorna campos para tabela de livros
     *
     * @return String[]
     */
    public String[] camposDoLivro() {
        LivroDAO dao = new LivroDAO();
        return dao.getNOMES();
    }

    /**
     * Retorna Array de String com dados do melhor preço de um livro
     *
     * @param idLivro int
     * @return String[]
     */
    public String[] getStringArrayMelhorPreco(int idLivro) {
        PrecoDAO dao = new PrecoDAO();
        Preco preco = dao.getMelhorPrecoPorIdLivro(idLivro);
        String[] retorno = new String[3];
        retorno[0] = preco.getLivro().getNome();
        retorno[1] = preco.getLivraria().getNome();
        retorno[2] = Formatacao.formDecimal(preco.getPreco());
        return retorno;
    }

    /**
     * Retorna dados para tabela
     *
     * @return String[][]
     */
    public String[][] dadosDaTabela() {
        LivroDAO dao = new LivroDAO();
        return dao.selecionarParaTabela();
    }

    /**
     * Exclui livro
     *
     * @param nome String
     */
    public void excluirLivro(String nome) {
        LivroDAO dao = new LivroDAO();
        dao.remover(dao.getLivroPorNome(nome));
    }

    /**
     * Inclui categoria
     *
     * @param categoria String
     */
    public void incluirCategoria(String categoria) {
        CategoriaDAO dao = new CategoriaDAO();
        if (categoria.length() <= 15) {
            dao.inserir(new Categoria(0, categoria));
        }
    }

    /**
     * Inclui livro
     *
     * @param nome String
     * @param autor String
     * @param categoria String
     */
    public void incluirLivro(String nome, String autor, String categoria) {
        LivroDAO dao = new LivroDAO();
        if (nome.length() <= 30 && autor.length() <= 25) {
            dao.inserir(nome, autor, categoria);
            Mediator mediator = new Mediator();
            mediator.inserirPrecos(nome);
        } else {
            Mensagem.msgErro("Nome do livro ou do autor longos demais");
        }
    }

    /**
     * Retorna todas categorias
     *
     * @return List
     */
    public List todasCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.selecionarTodos();
    }

    /**
     * Retorna todos valores de categorias
     *
     * @return List
     */
    public List valoresCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.getValoresCategorias();
    }

    /**
     * Construtor sem argumentos
     */
    public Controle() {
        BD banco = BD.obterBanco();
        banco.inicializa();
    }

}
