package br.com.cidandrade.aulas.cafe;

import br.com.cidandrade.aulas.enums.Tamanho;

public class ExtraForte extends Cafe {

    public ExtraForte() {
        descricao = "Café Extra-forte";
    }

    public ExtraForte(Tamanho tamanho) {
        descricao = "Café Extra-forte";
        super.tamanho = tamanho;
    }

    @Override
    public float preco() {
        float preco;
        switch (tamanho) {
            case PEQUENO:
                preco = 4F;
                break;
            case MEDIO:
                preco = 4.5F;
                break;
            default:
                preco = 5.5F;
                break;
        }
        return preco;
    }

}
