package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.cafe.*;
import br.com.cidandrade.aulas.cafe.adicional.*;
import br.com.cidandrade.aulas.enums.Tamanho;
import br.com.cidandrade.util.Mensagem;

public class Cafeteria {

    public static void main(String[] args) {
        Cafe cafe1 = new Expresso();
        Mensagem.mensagem(cafe1.getDescricaoCompleta());

        Cafe cafe2 = new ExtraForte(Tamanho.PEQUENO);
        cafe2 = new BitsDeChocolate(cafe2);
        cafe2 = new BitsDeChocolate(cafe2);
        cafe2 = new Chantilly(cafe2);
        Mensagem.mensagem(cafe2.getDescricaoCompleta());

        Cafe cafe3 = new CafeDaCasa();
        cafe3 = new LeiteVaporizado(cafe3);
        cafe3 = new Chantilly(cafe3);
        Mensagem.mensagem(cafe3.getDescricaoCompleta());
        
        System.exit(0);
    }

}
