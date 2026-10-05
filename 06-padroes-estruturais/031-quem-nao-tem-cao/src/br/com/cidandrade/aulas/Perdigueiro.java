package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Cachorro;
import br.com.cidandrade.util.Mensagem;

public class Perdigueiro implements Cachorro {

    @Override
    public void latir() {
        Mensagem.mensagem("Au, au, au");
    }

    @Override
    public void perseguir() {
        Mensagem.mensagem("Correr atrás de caça");
    }

    @Override
    public void abanarRabo() {
        Mensagem.mensagem("Abanar rabo");
    }

}
