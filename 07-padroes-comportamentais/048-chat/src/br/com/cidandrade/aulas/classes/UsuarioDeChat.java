package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.SalaDeChatIface;
import br.com.cidandrade.util.Mensagem;

public class UsuarioDeChat extends Usuario {

    public UsuarioDeChat(String id, String nome, SalaDeChatIface sala) {
        super(id, nome, sala);
    }

    @Override
    public void enviar(String msg, String usuario) {
        Mensagem.mensagem(this.getNome() + "::Enviando mensagem:" + msg);
        this.getSala().enviarMensagem(msg, usuario);
    }
    @Override
    public void enviarParaTodos(String msg) {
        Mensagem.mensagem(this.getNome() + "::Enviando mensagem:" + msg);
        this.getSala().enviarParaTodos(msg);
    }

    @Override
    public void receber(String msg) {
        Mensagem.mensagem(this.getNome() + "::Mensagem recebida:" + msg);
    }


}
