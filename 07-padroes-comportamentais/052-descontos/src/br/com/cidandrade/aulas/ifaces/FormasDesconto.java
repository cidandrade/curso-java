package br.com.cidandrade.aulas.ifaces;

public interface FormasDesconto {

    public double desconto(double valor);

    static FormasDesconto SemDesconto() {
        return valor -> valor;
    }

    static FormasDesconto DescontoDeNatal() {
        return valor -> valor * 0.9;
    }

    static FormasDesconto DescontoDeAnoNovo() {
        return valor -> valor * 0.8;
    }

    static FormasDesconto DescontoDePascoa() {
        return valor -> valor * 0.85;
    }
}
