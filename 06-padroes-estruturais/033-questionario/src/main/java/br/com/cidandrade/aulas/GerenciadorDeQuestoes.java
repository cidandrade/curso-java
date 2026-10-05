package br.com.cidandrade.aulas;

//Bridge
import br.com.cidandrade.aulas.ifaces.Questao;

public abstract class GerenciadorDeQuestoes {

    private Questao questao;
    private final String catalogo;

    public void proxima() {
        questao.proxima();
    }

    public void anterior() {
        questao.anterior();
    }

    public void nova(String pergunta) {
        questao.adicionar(pergunta);
    }

    public void apagar(String pergunta) {
        questao.remover(pergunta);
    }

    public String exibir() {
        return questao.mostrar();
    }

    public String exibirTodas() {
        StringBuilder sb = new StringBuilder("Questionário "
                + catalogo + "\n");
        sb.append(questao.mostrarTodas());
        return sb.toString();

    }

    public GerenciadorDeQuestoes(String catalogo, Questao questao) {
        this.catalogo = catalogo;
        this.questao = questao;
    }
}
