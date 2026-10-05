package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Questao;
import java.util.ArrayList;
import java.util.List;

public class Questoes implements Questao {

    private final List<String> questoes = new ArrayList<>();
    private int corrente = 0;

    @Override
    public void proxima() {
        if (corrente < questoes.size()) {
            corrente++;
        }
    }

    @Override
    public void anterior() {
        if (corrente > 0) {
            corrente--;
        }
    }

    @Override
    public void adicionar(String questao) {
        questoes.add(questao);
    }

    @Override
    public void remover(String questao) {
        questoes.remove(questao);
    }

    @Override
    public String mostrar() {
        return questoes.get(corrente);
    }

    @Override
    public String mostrarTodas() {
        StringBuilder sb = new StringBuilder();
        questoes.forEach(q -> sb.append(q)
                .append("\n"));
        return sb.toString();
    }

    public Questoes() {
        questoes.add("Qual bicho transmite a Doença de Chagas?");
        questoes.add("Qual é o coletivo de cães?");
        questoes.add("Qual é o triângulo que tem todos os "
                + "lados diferentes?");
        questoes.add("Quem compôs o Hino da Independência?");
        questoes.add("Qual é o antônimo de \"malograr\"?");
        questoes.add("Em qual país nasceu Carmen Miranda?");
        questoes.add("Seguindo a sequência do baralho, qual "
                + "carta vem depois do dez?");
        questoes.add("Que nome se dá à purificação por meio da água?");
        questoes.add("Em que parte do corpo se encontra a epiglote?");
        questoes.add("Qual animal está retratado na nota de dez reais?");
    }

}
