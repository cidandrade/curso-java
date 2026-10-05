package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.Observador;
import br.com.cidandrade.aulas.ifaces.ExibirElemento;
import br.com.cidandrade.aulas.ifaces.Sujeito;
import br.com.cidandrade.util.Mensagem;

public class Dispositivo implements Observador, ExibirElemento {

    private float temperatura, umidade;
    private Sujeito dadosMetereologicos;

    public Dispositivo(Sujeito dadosMetereologicos) {
        this.dadosMetereologicos = dadosMetereologicos;
        dadosMetereologicos.registrarObservador(this);
    }

    @Override
    public void atualizar(float temperatura, float umidade,
            float pressao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        exibir();
    }

    @Override
    public void exibir() {
        Mensagem.mensagem("Condições: " + temperatura + "ºC e "
                + umidade + "% de umidade");
    }

}
