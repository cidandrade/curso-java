/**
 * Pacote de classes que representam tabelas do banco de dados
 */
package br.com.cidandrade.aulas.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a tabela Categoria
 *
 * @author profandrade@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class Categoria {

    private int id;
    private String nome;
}
