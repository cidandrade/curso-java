package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Data;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa um mago
 *
 * @author cidandrade
 */
@AllArgsConstructor
@Getter
public class Mago implements Serializable {

    private String pnome, nomeMeio, sobrenome;
    private Endereco endereco;
    private LocalDate nascimento;
    private Casa casa;
    private transient String observacao;

    @Override
    public String toString() {
        return pnome + " " + nomeMeio + " " + sobrenome
                + ", da Casa " + casa.getNome() + ", nascido em "
                + Data.dataFormatada(nascimento)
                + ".\nResidente em " + endereco + 
                (observacao == null ? "" : "\n" + observacao);
    }

}
