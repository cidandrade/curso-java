package br.com.cidandrade.aulas.ifaces;

import br.com.cidandrade.aulas.classes.Usuario;

// Mediator
public interface SalaDeChatIface {

    public void enviarMensagem(String msg, String usuario);

    public void enviarParaTodos(String msg);

    public void adicionarUsuario(Usuario usuario);
}
