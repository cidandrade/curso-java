package br.com.cidandrade.aulas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cidandrade
 */
public class IOArquivo {

    public static void modoArquivo() {
        String nomeArquivo = "/tmp/testeJava.txt";
        File arquivo = new File(nomeArquivo);
        try {
            arquivo.createNewFile();
            try ( FileWriter fw = new FileWriter(arquivo, false)) {
                fw.write("Astolfo Viturino\n");
                fw.write("47\n");
                fw.write("1.79\n");
            }
            try ( Scanner sc = new Scanner(arquivo)) {
                String nome = sc.nextLine();
                byte idade = Byte.parseByte(sc.nextLine());
                float altura = Float.parseFloat(sc.nextLine());
                Mensagem.mensagemBasica(String.format(
                        "Prazer, %s, de %d anos de idade e "
                        + "%.2f de altura",
                        nome, idade, altura));
            }
        } catch (IOException ex) {
            Mensagem.mensagemDeErro("Erro na criação do arquivo");
        }
    }
}
