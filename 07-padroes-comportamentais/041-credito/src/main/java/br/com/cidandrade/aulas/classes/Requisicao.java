package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.enums.TipoDeCredito;
import br.com.cidandrade.util.Formatacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Requisicao {

    private Pessoa cliente;
    private TipoDeCredito tipo;
    private double valor;

    @Override
    public String toString() {

        return "Requisição de crédito " + tipo.getTipo()
                + " de R$ " + Formatacao.formDecimal(valor)
                + " para " + cliente.getNome() + ",\ncom salário "
                + "de R$ " + Formatacao.formDecimal(
                        cliente.getSalario())
                + (cliente.isTemCarro() ? (cliente.isTemImovel()
                ? ", com carro e imóvel." : ", com carro.")
                : (cliente.isTemImovel() ? ", com imóvel." : "."));
    }

}
