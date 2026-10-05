package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Arquivo;
import br.com.cidandrade.aulas.classes.OperadorDeArquivo;

public class ArquivoApp {

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo("/tmp/comd.txt");
        OperadorDeArquivo operador = new OperadorDeArquivo();
        operador.executarOperacao(arquivo::escrever);
        operador.executarOperacao(arquivo::ler);

        System.exit(0);
    }

}
