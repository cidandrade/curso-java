package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.OperacaoEmArquivo;
import br.com.cidandrade.util.Mensagem;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OpLerArquivo implements OperacaoEmArquivo {

    private final String nomeDoArquivo;

    public OpLerArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void executar() {
        Path caminho = Paths.get(nomeDoArquivo);
        List<String> conteudo;
        try {
            conteudo = Files.readAllLines(
                    caminho, StandardCharsets.UTF_16);
            conteudo.forEach(Mensagem::mensagem);
        } catch (IOException ex) {
            Mensagem.msgErro("Falha ao ler o arquivo", ex);
        }
    }

}
