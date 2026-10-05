package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Livro;
import br.com.cidandrade.util.Conv;
import java.util.List;

/**
 * DAO para livros
 *
 * @author cidandrade
 */
public class LivroDAO implements DAO<Livro> {

    private final String ALTERAR_SQL = "Update livros "
            + "set idCat = %d, nome = '%s', "
            + "autor = '%s' where id = %d";
    private final String CONTAR_TABELA = "Select "
            + "count(*) from livros";
    private final String INSERIR_SQL = "Insert into livros "
            + "(nome, autor, idCat) "
            + "values ('%s', '%s', %d)";
    private final String[] NOMES = {"ID", "Nome", "Autor", "Categoria"};
    private final String REMOVER_SQL = "Delete from livros where id = %d";
    private final String SELECIONAR_POR_ID = "Select * "
            + "from livros where id = %d";
    private final String SELECIONAR_POR_NOME = "Select * "
            + "from livros where nome = '%s'";
    private final String SELECIONAR_TABELA = "Select "
            + "livros.id, livros.nome as livro, autor, "
            + "categorias.nome as categoria "
            + "from livros, categorias "
            + "where livros.idCat = categorias.id "
            + "order by 2";

    /**
     * Alterar livro no banco de dados
     *
     * @param idLivro int
     * @param nome String
     * @param autor String
     * @param categoria String
     */
     void alterar(int idLivro, String nome,
            String autor, String categoria) {
        Mediator mediator = new Mediator();

        alterar(new Livro(idLivro, nome, autor,
                mediator.getCategoriaPorNome(categoria)));
    }

    /**
     * Retorna Livro em função do id
     *
     * @param id int
     * @return Livro
     */
     Livro getLivroPorId(int id) {
        Mediator mediator = new Mediator();

        Object[] dadosDoLivro = Util.getObjectArray(String.format(
                SELECIONAR_POR_ID, id));

        return new Livro(id,
                Conv.objectToString(dadosDoLivro[2]),
                Conv.objectToString(dadosDoLivro[3]),
                mediator.getCategoriaPorId(
                        Conv.objectToInteger(dadosDoLivro[1])));
    }

    /**
     * Retorna Livro dado seu nome
     *
     * @param nome String
     * @return Livro
     */
     Livro getLivroPorNome(String nome) {
        Mediator mediator = new Mediator();
        Object[] dadosDoLivro = Util.getObjectArray(String.format(
                SELECIONAR_POR_NOME, nome));
        return new Livro(Conv.objectToInteger(dadosDoLivro[0]),
                Conv.objectToString(dadosDoLivro[2]),
                Conv.objectToString(dadosDoLivro[3]),
                mediator.getCategoriaPorId(
                        Conv.objectToInteger(dadosDoLivro[1])));
    }

    /**
     * Retorna nomes para tabela
     *
     * @return String[]
     */
     String[] getNOMES() {
        return NOMES;
    }

    /**
     * Inserir livro no Banco de dados
     *
     * @param nome String
     * @param autor String
     * @param categoria String
     */
     void inserir(String nome, String autor, String categoria) {
        Mediator mediator = new Mediator();
        inserir(new Livro(0, nome, autor,
                mediator.getCategoriaPorNome(categoria)));
    }

    /**
     * Retorna conteúdo para tabela
     *
     * @return String[][]
     */
     String[][] selecionarParaTabela() {
        String[][] retorno;
        int qtdeLivros, qtdeCampos;
        int posicao = 0;

        qtdeLivros = Util.getValor(CONTAR_TABELA);
        qtdeCampos = getNOMES().length;
        retorno = new String[qtdeLivros][qtdeCampos];
        List lista = Util.getList(SELECIONAR_TABELA);
        for (Object dadosLista : lista) {
            Object[] dadosLinha = Conv.objectToObjectArray(dadosLista);
            String[] linha = new String[qtdeCampos];
            linha[0] = Conv.objectToValueString(dadosLinha[0]);
            linha[1] = Conv.objectToString(dadosLinha[1]);
            linha[2] = Conv.objectToString(dadosLinha[2]);
            linha[3] = Conv.objectToString(dadosLinha[3]);
            retorno[posicao++] = linha;
        }
        return retorno;
    }

    /**
     * Altera livro no banco de dados
     *
     * @param livro Livro
     */
    @Override
    public void alterar(Livro livro) {
        Util.executa(String.format(ALTERAR_SQL,
                livro.getCategoria().getId(), livro.getNome(),
                livro.getAutor(), livro.getId()));
    }

    /**
     * Insere livro no banco de dados
     *
     * @param livro Livro
     */
    @Override
    public void inserir(Livro livro) {
        Util.executa(String.format(INSERIR_SQL,
                livro.getNome(), livro.getAutor(),
                livro.getCategoria().getId()));
    }

    /**
     * Remove livro do banco de dados
     *
     * @param livro Livro
     */
    @Override
    public void remover(Livro livro) {
        Mediator mediator = new Mediator();

        mediator.removerPorLivro(livro);
        Util.executa(String.format(REMOVER_SQL,
                livro.getId()));
    }

    /**
     * Selecionar todos Não implementado
     *
     * @return List
     */
    @Override
    public List<Livro> selecionarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
