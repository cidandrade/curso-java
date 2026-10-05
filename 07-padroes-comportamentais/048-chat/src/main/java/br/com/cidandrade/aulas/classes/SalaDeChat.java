package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.SalaDeChatIface;
import java.util.HashMap;
import java.util.Map;

// Mediator Concreto
public class SalaDeChat implements SalaDeChatIface {

    private final Map<String, Usuario> usuarios;

    public SalaDeChat() {
        usuarios = new HashMap<>();
    }

    @Override
    public void enviarMensagem(String msg, String usuario) {
        Usuario u = usuarios.get(usuario);
        u.receber(msg);
    }

    @Override
    public void enviarParaTodos(String msg) {
        for (Usuario u : usuarios.values()) {
            enviarMensagem(msg, u.getId());
        }
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

}
