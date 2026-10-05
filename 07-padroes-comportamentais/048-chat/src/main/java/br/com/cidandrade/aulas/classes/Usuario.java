package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.SalaDeChatIface;
import lombok.AllArgsConstructor;
import lombok.Getter;

// Colega
@AllArgsConstructor
@Getter
public abstract class Usuario {

    private String id;
    private String nome;
    private SalaDeChatIface sala;

    public abstract void enviar(String msg, String usuario);

    public abstract void enviarParaTodos(String msg);

    public abstract void receber(String msg);

}
