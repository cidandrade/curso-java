package br.com.cidandrade.aulas.ensaio;

import br.com.cidandrade.aulas.enums.TipoEnsaio;
import br.com.cidandrade.util.Mensagem;

public abstract class Estudio {

    public Ensaio contrataEnsaio(TipoEnsaio tipo) {
        Ensaio ensaio = criaEnsaio(tipo);
        ensaio.preparar();
        Mensagem.mensagem(ensaio.getNome());
        Mensagem.mensagem(ensaio.fundo.getFundo());
        Mensagem.mensagem(ensaio.figurino.getFigurino());
        Mensagem.mensagem(ensaio.acessorio.getAcessorio());
        Mensagem.mensagem(ensaio.ambiente.getAmbiente());
        return ensaio;
    }

    public abstract Ensaio criaEnsaio(TipoEnsaio tipo);

}
