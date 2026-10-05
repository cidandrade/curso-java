package br.com.cidandrade.aulas.cafe.adicional;

import br.com.cidandrade.aulas.cafe.Cafe;

public class BitsDeChocolate extends Adicional {

    Cafe cafe;

    public BitsDeChocolate(Cafe cafe) {
        this.cafe = cafe;
        setTamanho(cafe.getTamanho());
    }

    @Override
    public String getDescricao() {
        return String.format("%s, bits de chocolate", 
                cafe.getDescricao());
    }

    @Override
    public float preco() {
        float preco = cafe.preco();
        switch (cafe.getTamanho()) {
            case PEQUENO:
                preco += 1.2F;
                break;
            case MEDIO:
                preco += 1.6F;
                break;
            default:
                preco += 2.2F;
                break;
        }
        return preco;
    }

}
