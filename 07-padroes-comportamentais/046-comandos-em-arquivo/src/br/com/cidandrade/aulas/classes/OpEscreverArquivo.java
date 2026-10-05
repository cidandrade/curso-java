package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.OperacaoEmArquivo;
import br.com.cidandrade.util.Mensagem;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpEscreverArquivo implements OperacaoEmArquivo {

    private final String nomeDoArquivo;

    public OpEscreverArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void executar() {
        Path caminho = Paths.get(nomeDoArquivo);
        String conteudo = "123456";
        byte[] bytes = conteudo.getBytes(StandardCharsets.UTF_16);
        try {
            Files.write(caminho, bytes);
        } catch (IOException ex) {
            Mensagem.msgErro("Falha ao escrever em arquivo", ex);
            System.exit(1);
        }
    }

}
