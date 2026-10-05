package br.com.cidandrade.aulas.classes.comport;

import br.com.cidandrade.aulas.ifaces.ComportDieta;
import br.com.cidandrade.util.Mensagem;

public class DietaNormal implements ComportDieta {

    @Override
    public void comer() {
        Mensagem.mensagem("Dieta normal");
    }

}
