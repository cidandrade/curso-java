package br.com.cidandrade.aulas.classes;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa um endereço
 *
 * @author cidandrade
 */
@AllArgsConstructor
@Getter
public class Endereco implements Serializable {

    private String tipo, logradouro, numero,
            complemento, bairro, cidade, regiao, pais;

    @Override
    public String toString() {
        return tipo + " " + logradouro + ", " + numero
                + " - " + complemento + ". " + bairro + ",\n" + cidade
                + ", " + regiao + ". " + pais.toUpperCase();
    }

}
