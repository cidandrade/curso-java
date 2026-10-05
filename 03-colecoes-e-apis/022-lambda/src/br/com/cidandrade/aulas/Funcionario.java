package br.com.cidandrade.aulas;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Funcionario {

    private String nome;
    Sexo sexo;
    LocalDate nascimento;
    String email;

    public int getIdade() {
        return Period.between(
                nascimento,
                LocalDate.of(1981,
                        Month.JANUARY, 1)).getYears();
    }

    public void imprimeFuncionario() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return nome + " (" + sexo.getSexo() + "), "
                + getIdade() + " anos - " + email;
    }

    public enum Sexo {
        MASC("Masculino"), FEM("Feminino"),
        OUTROS("Outros");
        private final String sexo;

        private Sexo(String sexo) {
            this.sexo = sexo;
        }

        public String getSexo() {
            return sexo;
        }

    }
}
