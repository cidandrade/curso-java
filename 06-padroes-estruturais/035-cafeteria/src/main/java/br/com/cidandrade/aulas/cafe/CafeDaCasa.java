package br.com.cidandrade.aulas.cafe;

import br.com.cidandrade.aulas.enums.Tamanho;

public class CafeDaCasa extends Cafe {

    public CafeDaCasa() {
        descricao = "Café da Casa";
    }

    public CafeDaCasa(Tamanho tamanho) {
        descricao = "Café da Casa";
        super.tamanho = tamanho;
    }

    @Override
    public float preco() {
        float preco;
        switch (tamanho) {
            case PEQUENO:
                preco = 8.5F;
                break;
            case MEDIO:
                preco = 9.5F;
                break;
            default:
                preco = 10.5F;
                break;
        }
        return preco;
    }

}
