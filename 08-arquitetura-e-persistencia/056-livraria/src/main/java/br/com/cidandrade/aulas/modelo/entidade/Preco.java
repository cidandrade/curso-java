package br.com.cidandrade.aulas.modelo.entidade;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a tabela Preco
 *
 * @author profandrade@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
public class Preco {

    private Livro livro;
    private Livraria livraria;
    private LocalDate data;
    private float preco;
}
