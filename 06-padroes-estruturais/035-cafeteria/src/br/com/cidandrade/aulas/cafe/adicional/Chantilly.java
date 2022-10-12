package br.com.cidandrade.aulas.cafe.adicional;

import br.com.cidandrade.aulas.cafe.Cafe;

public class Chantilly extends Adicional {

    Cafe cafe;

    public Chantilly(Cafe cafe) {
        this.cafe = cafe;
        setTamanho(cafe.getTamanho());
    }

    @Override
    public String getDescricao() {
        return cafe.getDescricao() + ", chantilly";
    }

    @Override
    public float preco() {
        float preco = cafe.preco();
        switch (cafe.getTamanho()) {
            case PEQUENO:
                preco += 1.6F;
                break;
            case MEDIO:
                preco += 2.2F;
                break;
            default:
                preco += 3F;
                break;
        }
        return preco;
    }

}
