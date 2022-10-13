package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Formatacao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author cidandrade
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Retangulo extends FormaGeometrica {

    private double base, altura;

    @Override
    public double getArea() {
        return base * altura;
    }

    @Override
    public String getDescricao() {
        return toString() + " / Área: " 
                + Formatacao.formDecimal(getArea());
    }

    @Override
    public String toString() {
        var baseFormatada = Formatacao.formDecimal(base);
        var alturaFormatada = Formatacao.formDecimal(altura);
        return "Retângulo com base de " + baseFormatada
                + " e altura de " + alturaFormatada;
    }

}
