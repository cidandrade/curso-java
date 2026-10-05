package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Questao;

public class FormatadorDeQuestoes extends GerenciadorDeQuestoes {

    public FormatadorDeQuestoes(String catalogo, Questao questao) {
        super(catalogo, questao);
    }

    @Override
    public String exibirTodas() {
        String barra = "================";
        StringBuilder sb = new StringBuilder(barra + "\n");
        sb.append(super.exibirTodas());
        sb.append(barra);
        return sb.toString();
    }

}
