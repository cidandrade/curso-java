package br.com.cidandrade.aulas.cafe;

import br.com.cidandrade.aulas.enums.Tamanho;

public abstract class Cafe {

    String descricao;
    Tamanho tamanho;

    public abstract float preco();

    public String getDescricaoCompleta() {
        return String.format("%s - R$ %.2f", 
                getDescricao(), preco());
    }

    public Cafe() {
        descricao = "Desconhecido";
        tamanho = Tamanho.MEDIO;
    }

    public String getDescricao() {
        return descricao + " (" + tamanho.getTamanho() + ")";
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

}
