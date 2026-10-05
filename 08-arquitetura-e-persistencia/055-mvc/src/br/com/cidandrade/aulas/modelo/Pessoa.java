/**
 * Classes que implementam os dados manipulados pela aplicação
 */
package br.com.cidandrade.aulas.modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import lombok.Getter;

/**
 * Representa uma pessoa
 *
 */
@Getter
public class Pessoa {

    private String nome, profissao, religiao;
    private final PropertyChangeSupport pcs
            = new PropertyChangeSupport(this);

    /**
     * Construtor sem argumentos
     */
    public Pessoa() {
        this.nome = "Cid Rodrigues de Andrade";
        this.profissao = "Professor";
        this.religiao = "Umbandista";
    }

    /**
     * Construtor com todos argumentos
     *
     * @param nome - String: nome da pessoa
     * @param profissao - String: profissão da pessoa
     * @param religiao - String: religião da pessoa
     */
    public Pessoa(String nome, String profissao, String religiao) {
        this.nome = nome;
        this.profissao = profissao;
        this.religiao = religiao;
    }

    /**
     * Retorna um objeto da classe
     *
     * @return Pessoa
     */
    public Pessoa getPessoa() {
        return this;
    }

    /**
     * Setter do campo nome
     *
     * @param nome - String: nome da pessoa
     */
    public void setNome(String nome) {
        pcs.firePropertyChange("Pessoa",
                new Pessoa(this.nome, profissao, religiao),
                new Pessoa(nome, profissao, religiao));
        this.nome = nome;
    }

    /**
     * Setter do campo profissão
     *
     * @param profissao - String: profissão da pessoa
     */
    public void setProfissao(String profissao) {
        pcs.firePropertyChange("Pessoa",
                new Pessoa(nome, this.profissao, religiao),
                new Pessoa(nome, profissao, religiao));
        this.profissao = profissao;
    }

    /**
     * Setter do campo religiao
     *
     * @param religiao - String: religião da pessoa
     */
    public void setReligiao(String religiao) {
        pcs.firePropertyChange("Pessoa",
                new Pessoa(nome, profissao, this.religiao),
                new Pessoa(nome, profissao, religiao));
        this.religiao = religiao;
    }

    /**
     * Registra observador - Design Patter observer
     *
     * @param o - ProperyChangeListener: objeto observador
     */
    public void registrarObservador(PropertyChangeListener o) {
        pcs.addPropertyChangeListener(o);
    }

}
