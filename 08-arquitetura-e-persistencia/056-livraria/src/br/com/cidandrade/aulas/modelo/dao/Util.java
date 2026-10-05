package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.BD;
import br.com.cidandrade.util.Conv;
import java.util.List;

/**
 * Classe utilitária de operações no banco de dados
 */
public class Util {

    /**
     * Executa instrução SQL
     *
     * @param sql String
     */
    static void executa(String sql) {
        BD bd = BD.obterBanco();
        bd.executa(sql);
    }

    /**
     * Retorna List com resultados de pesquisa SQL
     *
     * @param sql String
     * @return List
     */
    static List getList(String sql) {
        BD bd = BD.obterBanco();
        return bd.getSQLDados(sql);
    }

    /**
     * Retorna Array de Object com dados de uma linha de uma consulta SQL
     *
     * @param sql String
     * @return Object[]
     */
    static Object[] getObjectArray(String sql) {
        List lista = getList(sql);
        return Conv.objectToObjectArray(lista.get(0));
    }

    /**
     * Retorna inteiro de primeiro campo de primeira linha de uma consulta SQL
     *
     * @param sql String
     * @return int
     */
    static int getValor(String sql) {
        BD bd = BD.obterBanco();
        return bd.getValorConsulta(sql);
    }
}
