package br.com.cidandrade.aulas.classes;

public class Arquivo {

    private final String arquivo;
    private final OperadorDeArquivo operador;

    public Arquivo(String arquivo) {
        this.arquivo = arquivo;
        operador = new OperadorDeArquivo();
    }

    public void escrever() {
        operador.executarOperacao(new OpEscreverArquivo(
                arquivo));
    }

    public void ler() {
        operador.executarOperacao(new OpLerArquivo(arquivo));
    }
}
