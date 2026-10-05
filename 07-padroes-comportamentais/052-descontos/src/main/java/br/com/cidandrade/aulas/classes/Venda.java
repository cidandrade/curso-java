package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.ifaces.FormasDesconto;
import lombok.Setter;

@Setter
public class Venda {

    private double valor;
    FormasDesconto formadesconto;

    public double cobrar() {
        return formadesconto.desconto(valor);
    }

    public Venda(double valor) {
        this.valor = valor;
        this.formadesconto = FormasDesconto.SemDesconto();
    }
}
