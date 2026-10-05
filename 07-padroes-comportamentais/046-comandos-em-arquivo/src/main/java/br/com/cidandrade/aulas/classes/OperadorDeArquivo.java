package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.OperacaoEmArquivo;
import java.util.ArrayList;
import java.util.List;

public class OperadorDeArquivo {

    private final List<OperacaoEmArquivo> operacoes
            = new ArrayList<>();

    public OperadorDeArquivo() {
    }

    public void executarOperacao(OperacaoEmArquivo operacao) {
        operacoes.add(operacao);
        operacao.executar();
    }
}
