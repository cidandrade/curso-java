package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.SalaDeChat;
import br.com.cidandrade.aulas.classes.Usuario;
import br.com.cidandrade.aulas.classes.UsuarioDeChat;
import br.com.cidandrade.aulas.ifaces.SalaDeChatIface;

public class ChatApp {

    public static void main(String[] args) {
        SalaDeChatIface sala = new SalaDeChat();

        Usuario u1 = new UsuarioDeChat("Rei", "Charles III", sala);
        Usuario u2 = new UsuarioDeChat("Rainha", "Camila", sala);
        Usuario u3 = new UsuarioDeChat("Príncipe", "William", sala);
        Usuario u4 = new UsuarioDeChat("Princesa", "Catherine", sala);

        sala.adicionarUsuario(u1);
        sala.adicionarUsuario(u2);
        sala.adicionarUsuario(u3);
        sala.adicionarUsuario(u4);

        u1.enviar("Passa aqui", "Príncipe");
        u3.enviar("Pede para o Charles esperar", "Rainha");
        u1.enviarParaTodos("Reunião agora!!!");
    }

}
