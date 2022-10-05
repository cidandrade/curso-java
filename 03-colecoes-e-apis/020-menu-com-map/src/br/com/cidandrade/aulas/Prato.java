package br.com.cidandrade.aulas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Prato {

    private String nome, descricao;
    private float preco;

    @Override
    public String toString() {
        return String.format("%s\n%s - R$ %.2f",
                nome, descricao, preco);
    }

}
