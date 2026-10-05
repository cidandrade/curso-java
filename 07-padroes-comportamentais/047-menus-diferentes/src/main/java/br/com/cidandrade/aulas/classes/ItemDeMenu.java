package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Formatacao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDeMenu {

    String nome, descricao;
    boolean vegetariano;
    float preco;

    @Override
    public String toString() {
        return nome + " (" + descricao
                + (vegetariano ? "V" : "") + ") - R$ "
                + Formatacao.formDecimal(preco);
    }

}
