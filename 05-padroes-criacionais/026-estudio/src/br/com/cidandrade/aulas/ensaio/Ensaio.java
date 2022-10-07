package br.com.cidandrade.aulas.ensaio;

import br.com.cidandrade.util.Mensagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ensaio {

    String nome;

    public void preparaFundo() {
        Mensagem.mensagem("Prepara fundo para ensaio");
    }

    public void separaFigurino() {
        Mensagem.mensagem("Separa figurino para ensaio");
    }

    public void separaAcessorios() {
        Mensagem.mensagem("Separa acessórios para ensaio");
    }

    public void itensDeAmbiente() {
        Mensagem.mensagem("Ajusta o ambiente para ensaio");
    }

}
