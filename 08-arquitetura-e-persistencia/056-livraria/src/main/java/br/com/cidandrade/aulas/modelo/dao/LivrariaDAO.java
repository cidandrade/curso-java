package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Livraria;
import br.com.cidandrade.util.Conv;
import java.util.ArrayList;
import java.util.List;

public class LivrariaDAO implements DAO<Livraria> {

    private final String SELECIONAR_POR_ID = "Select * "
            + "from livrarias where id = %d";
    private final String SELECIONAR_SQL = "Select * "
            + "from livrarias";

    /**
     * Retorna Livraria, dado seu id
     *
     * @param id int
     * @return Livraria
     */
     Livraria getLivrariaPorId(int id) {
        Object[] dadosDaLivraria = Util.getObjectArray(
                String.format(SELECIONAR_POR_ID, id));
        return new Livraria((byte) id,
                Conv.objectToString(dadosDaLivraria[1]));
    }

    /**
     * Seleciona todas livrarias
     *
     * @return List
     */
    @Override
    public List selecionarTodos() {
        List retorno = new ArrayList();
        for (Object dados : Util.getList(SELECIONAR_SQL)) {
            Object[] linha = Conv.objectToObjectArray(dados);
            int id = Conv.objectToInteger(linha[0]);
            String nome = Conv.objectToString(linha[1]);
            retorno.add(new Livraria(id, nome));
        }
        return retorno;
    }

    /**
     * Alterar livraria
     *
     * Não implementado
     *
     * @param livraria Livraria
     */
    @Override
    public void alterar(Livraria livraria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Inserir livraria
     *
     * Não implementado
     *
     * @param livraria Livraria
     */
    @Override
    public void inserir(Livraria livraria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Remover livraria
     *
     * Não implementado
     *
     * @param livraria Livraria
     */
    @Override
    public void remover(Livraria livraria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
