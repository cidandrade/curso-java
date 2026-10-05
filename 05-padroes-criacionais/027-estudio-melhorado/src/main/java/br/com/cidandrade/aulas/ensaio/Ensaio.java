package br.com.cidandrade.aulas.ensaio;

import br.com.cidandrade.aulas.preparativo.Acessorio;
import br.com.cidandrade.aulas.preparativo.Ambiente;
import br.com.cidandrade.aulas.preparativo.Figurino;
import br.com.cidandrade.aulas.preparativo.Fundo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Ensaio {

    public String nome;
    public Fundo fundo;
    public Figurino figurino;
    public Acessorio acessorio;
    public Ambiente ambiente;

    public abstract void preparar();
}
