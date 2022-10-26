package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Categoria;
import br.com.cidandrade.util.Conv;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria> {

    private final String INSERIR_SQL = "Insert into categorias "
            + "(nome) values ('%s')";
    private final String SELECIONAR_POR_ID = "Select * "
            + "from categorias where id = %d";
    private final String SELECIONAR_POR_NOME = "Select * "
            + "from categorias where nome = '%s'";
    private final String SELECIONAR_SQL = "Select * "
            + "from categorias order by nome";

    /**
     * Retorna Categoria, dado seu id
     *
     * @param id int
     * @return Categoria
     */
     Categoria getCategoriaPorId(int id) {
        Object[] dadosDaCategoria = Util.getObjectArray(String.format(
                SELECIONAR_POR_ID, id));
        return new Categoria((byte) id,
                Conv.objectToString(dadosDaCategoria[1]));
    }

    /**
     * Retorna Categoria, dado seu nome
     *
     * @param nome String
     * @return Categoria
     */
    Categoria getCategoriaPorNome(String nome) {
        Object[] dadosDaCategoria = Util.getObjectArray(String.format(
                SELECIONAR_POR_NOME, nome));
        return new Categoria(
                Conv.objectToInteger(dadosDaCategoria[0]), nome);
    }

    /**
     * Retorna nome das categorias
     *
     * @return List
     */
    List getValoresCategorias() {
        List categorias = selecionarTodos();
        List valores = new ArrayList();
        for (Object c : categorias) {
            Categoria categoria = (Categoria) c;
            valores.add(categoria.getNome());
        }
        return valores;
    }

    /**
     * Insere categoria
     *
     * @param categoria Categoria
     */
    @Override
    public void inserir(Categoria categoria) {
        Util.executa(String.format(
                INSERIR_SQL, categoria.getNome()));
    }

    /**
     * Seleciona todas as categorias
     *
     * @return List
     */
    @Override
    public List selecionarTodos() {
        List retorno = new ArrayList();
        for (Object dados : Util.getList(SELECIONAR_SQL)) {
            Object[] linha = Conv.objectToObjectArray(dados);
            byte id = Conv.objectToValueByte(linha[0]);
            String nome = Conv.objectToString(linha[1]);
            retorno.add(new Categoria(id, nome));
        }
        return retorno;
    }

    /**
     * Alterar
     *
     * Não implementado
     *
     * @param categoria Categoria
     */
    @Override
    public void alterar(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Remover
     *
     * Não implementado
     *
     * @param categoria Categoria
     */
    @Override
    public void remover(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
