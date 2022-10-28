package br.com.cidandrade.aulas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Implementa um Servidor TCP na porta 12345. Executar este programa e, enquanto
 * ele estiver rodando, execute o cliente TCP, que também se encontra no Github
 *
 * @author cidandrade
 */
public class Servidor {

    public static void main(String[] args) {
        System.out.println("Servidor iniciando ...");
        try {
            ServerSocket sSoquete = new ServerSocket(12345);
            while (true) {
                try ( Socket cliente = sSoquete.accept()) {
                    System.out.println("Dispara Thread");
                    new ThreadCliente(cliente).run();
                }
            }
        } catch (IOException ex) {
            System.out.println("Falha no servidor!!!\n"
                    + ex.getLocalizedMessage());
        }
    }

}
