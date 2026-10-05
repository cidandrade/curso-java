package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Mensagem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Manipula objetos serializados
 *
 * @author cidandrade
 */
public class ManipulaObjeto {

    public static void salva(String arquivo, Object o) {
        try {
            try ( FileOutputStream fos
                    = new FileOutputStream("/tmp/" + arquivo);  
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(o);
            }
        } catch (IOException ex) {
            Mensagem.msgErro("Falha ao salvar arquivo\n", ex);
        }
    }

    public static Mago recupera(String arquivo) {
        Mago mago = null;
        try {
            try ( FileInputStream fis = new FileInputStream("/tmp/" + arquivo);  
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                mago = (Mago) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Mensagem.msgErro("Falha ao recuperar arquivo\n", ex);
        }
        return mago;
    }
}
