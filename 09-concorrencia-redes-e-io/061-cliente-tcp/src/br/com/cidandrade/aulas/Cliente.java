package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Conv;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author cidandrade
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            Socket soquete = new Socket("localhost", 12345);
            try (ObjectOutputStream oos 
                    = new ObjectOutputStream(soquete.getOutputStream())) {
                oos.writeObject("Oi");
                try (ObjectInputStream ois 
                        = new ObjectInputStream(soquete.getInputStream())) {
                    String resposta = Conv.objectToString(ois.readObject());
                    System.out.println("Resposta: \n" + resposta);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erro no acesso ao servidor\n"
                    + ex.getLocalizedMessage());
        }
    }

}
