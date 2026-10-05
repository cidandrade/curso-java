package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class QuestApp {

    public static void main(String[] args) {
        FormatadorDeQuestoes questionario
                = new FormatadorDeQuestoes("Show dos Dez Reais", 
                        new Questoes());
        Mensagem.mensagem(questionario.exibirTodas());
    }

}
