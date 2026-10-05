package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Formatacao;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Triangulo extends FormaGeometrica {

    private double base, altura;

    @Override
    public double getArea() {
        return base * altura / 2;
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
        return "Triângulo com base de " + baseFormatada
                + " e altura de " + alturaFormatada;
    }

}
