package br.com.cidandrade.aulas.enums;

public enum TipoDeCredito {
    PESSOAL("Pessoal"), CONSIGNADO("Consignado"),
    GARANTIA_IMOVEL("com Imóvel em Garantia"),
    GARANTIA_CARRO("com Veículo em Garantia");
    private final String tipo;

    private TipoDeCredito(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
