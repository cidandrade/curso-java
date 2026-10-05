package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.interfaces.ChecarFunc;

/**
 *
 * @author cidandrade
 */
public class Checagem implements ChecarFunc {

    @Override
    public boolean teste(Funcionario f) {
        return f.getIdade() <= 20 && f.getIdade() > 12
                && f.getSexo() != f.sexo.MASC;
    }

}
