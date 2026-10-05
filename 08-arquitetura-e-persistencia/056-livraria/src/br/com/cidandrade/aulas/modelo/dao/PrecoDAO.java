package br.com.cidandrade.aulas.modelo.dao;

import br.com.cidandrade.aulas.modelo.entidade.Livro;
import br.com.cidandrade.aulas.modelo.entidade.Preco;
import br.com.cidandrade.util.Conv;
import br.com.cidandrade.util.Data;
import br.com.cidandrade.util.Formatacao;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe DAO da entidade Preco
 */
public class PrecoDAO implements DAO<Preco> {

    private final String INSERIR_SQL = "Insert into precos "
            + "(idLivro, idLivraria, data, preco) "
            + "values (%d, %d, '%s', %s)";
    private final String REMOVER_SQL_LIVRO = "Delete from precos "
            + "where idLivro = %d";
    private final String SELECIONAR_MELHOR_PRECO = "Select * from precos "
            + "where idLivro = %d and "
            + "preco = (select min(preco) "
            + "from precos where idLivro = %d)";

    /**
     * Retorna melhor preco de livro dado seu id
     *
     * @param id int
     * @return Preco
     */
     Preco getMelhorPrecoPorIdLivro(int id) {
        Mediator mediator = new Mediator();
        Object[] melhorPreco = Util.getObjectArray(
                String.format(SELECIONAR_MELHOR_PRECO,
                        id, id));
        int idLivro = Conv.objectToInteger(melhorPreco[0]);
        int idLivraria = Conv.objectToInteger(melhorPreco[1]);
        LocalDate data = Data.dateToLocalDate((Date) melhorPreco[2]);
        float preco = Conv.objectToFloat(melhorPreco[3]);
        return new Preco(mediator.getLivroPorId(idLivro),
                mediator.getLivrariaPorId(idLivraria),
                data, preco);

    }

    /**
     * Remover todos os preços relativos a um livro
     *
     * @param livro Livro
     */
    void removerPorLivro(Livro livro) {
        Util.executa(String.format(
                REMOVER_SQL_LIVRO, livro.getId()));
    }

    /**
     * Inserir Preço no banco de dados
     *
     * @param preco Preco
     */
    @Override
    public void inserir(Preco preco) {
        Util.executa(String.format(INSERIR_SQL,
                preco.getLivro().getId(),
                preco.getLivraria().getId(),
                preco.getData(),
                Formatacao.formDecimalComPonto(preco.getPreco())));
    }

    /**
     * Alterar preco no banco de dados Não implementado
     *
     * @param preco Preco
     */
    @Override
    public void alterar(Preco preco) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Remover preco no banco de dados Não implementado
     *
     * @param preco Preco
     */
    @Override
    public void remover(Preco preco) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Selecionar todos os precos no banco de dados Não implementado
     *
     * @return List
     */
    @Override
    public List<Preco> selecionarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
