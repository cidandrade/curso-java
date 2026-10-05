package br.com.cidandrade.aulas.cafe;

import br.com.cidandrade.aulas.enums.Tamanho;

public class Expresso extends Cafe {

    public Expresso() {
        descricao = "Expresso";
    }

    public Expresso(Tamanho tamanho) {
        descricao = "Expresso";
        super.tamanho = tamanho;
    }

    @Override
    public float preco() {
        float preco;
        switch (tamanho) {
            case PEQUENO:
                preco = 5F;
                break;
            case MEDIO:
                preco = 6F;
                break;
            default:
                preco = 7F;
                break;
        }
        return preco;
    }

}
