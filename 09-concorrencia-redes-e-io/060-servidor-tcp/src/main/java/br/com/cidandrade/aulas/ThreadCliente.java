package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Conv;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Implementa a Thread que atende às requisições do cliente, deixando o servidor
 * livre para continuar "escutando" novos clientes
 *
 * @author cidandrade
 */
public class ThreadCliente extends Thread {

    private final Socket cliente;

    public ThreadCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            try (ObjectInputStream ois 
                    = new ObjectInputStream((cliente.getInputStream()))) {
                String msg = Conv.objectToString(ois.readObject());
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        cliente.getOutputStream())) {
                    oos.flush();
                    oos.writeObject("Echo " + msg);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Falha no processamento do cliente\n"
                    + ex.getLocalizedMessage());
        }
    }

}
