/**
 * Classes que implementam a lógica de funcionamento da aplicação
 */
package br.com.cidandrade.aulas.controle;

import br.com.cidandrade.aulas.modelo.Pessoa;
import java.beans.PropertyChangeListener;

/**
 * Esta classe é um Facade que dá tem a lógica de funcionamento e envia
 * instruções ao Modelo
 *
 * @author cidandrade
 */
public class Controle {

    private final Pessoa pessoa;

    /**
     * Construtor da classe
     */
    public Controle() {
        pessoa = new Pessoa();
    }

    /**
     * Método que retorna uma instância da classe Pessoa
     *
     * @see Pessoa
     * @return Pessoa - Objeto Pessoa
     */
    public Pessoa getPessoa() {
        return pessoa.getPessoa();
    }

    /**
     * Altera nome da pessoa
     *
     * @param nome - String novo nome
     */
    public void alterarNome(String nome) {
        if (nome != null) {
            String nomeTrim = nome.trim();
            if (!nomeTrim.equals("")) {
                pessoa.setNome(nomeTrim);
            }
        }
    }

    /**
     * Altera profissão da pessoa
     *
     * @param profissao - String nova profissão
     */
    public void alterarProfissao(String profissao) {
        if (profissao != null) {
            String profissaoTrim = profissao.trim();
            if ("Arquiteto|Professor|Fotógrafo|Desenvolvedor|Astrólogo"
                    .contains(profissaoTrim)
                    && !profissaoTrim.equals("")) {
                pessoa.setProfissao(profissaoTrim);
            }
        }
    }

    /**
     * Altera religião da pessoa
     *
     * @param religiao - String nova religião
     */
    public void alterarReligiao(String religiao) {
        if (religiao != null) {
            String religiaoTrim = religiao.trim();
            if ("Budista|Umbandista|Católico|Judeu"
                    .contains(religiaoTrim)
                    && !religiaoTrim.equals("")) {
                pessoa.setReligiao(religiaoTrim);
            }
        }
    }

    /**
     * Registra observador de acordo com Design Pattern Observer
     *
     * @param o - PropertyChangeListener - Objeto observador
     */
    public void registrarObservador(PropertyChangeListener o) {
        pessoa.registrarObservador(o);
    }
}
