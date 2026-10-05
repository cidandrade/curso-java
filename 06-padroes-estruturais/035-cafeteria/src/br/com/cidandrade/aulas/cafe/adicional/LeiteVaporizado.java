package br.com.cidandrade.aulas.cafe.adicional;

import br.com.cidandrade.aulas.cafe.Cafe;

public class LeiteVaporizado extends Adicional {

    Cafe cafe;

    public LeiteVaporizado(Cafe cafe) {
        this.cafe = cafe;
        setTamanho(cafe.getTamanho());
    }

    @Override
    public String getDescricao() {
        return cafe.getDescricao() + ", leite vaporizado";
    }

    @Override
    public float preco() {
        float preco = cafe.preco();
        switch (cafe.getTamanho()) {
            case PEQUENO:
                preco += 0.8F;
                break;
            case MEDIO:
                preco += 1F;
                break;
            default:
                preco += 1.3F;
                break;
        }
        return preco;
    }

}
