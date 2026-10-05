package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.interfaces.ChecarFunc;
import java.util.List;

public class RHApp {

    public static void main(String[] args) {
        Lista funcionarios = new Lista();
        // Método 1
        System.out.println("\nMétodo 1");
        imprimir1(funcionarios.getFuncionarios());
        // Método 2
        System.out.println("\nMétodo 2");
        imprimir2(funcionarios.getFuncionarios(), new Checagem());
        // Método 3
        System.out.println("\nMétodo 3");
        imprimir2(funcionarios.getFuncionarios(), new ChecarFunc() {
            @Override
            public boolean teste(Funcionario f) {
                return f.getIdade() <= 20 && f.getIdade() > 12
                        && f.getSexo() != f.sexo.MASC;
            }
        });
        // Método 4 (Lambda)
        System.out.println("\nMétodo 4");
        imprimir2(funcionarios.getFuncionarios(),
                (Funcionario f) -> f.getIdade() <= 20
                && f.getIdade() > 12
                && f.getSexo() != f.sexo.MASC);
    }

    private static void imprimir1(List<Funcionario> func) {
        for (Funcionario f : func) {
            if (f.getIdade() <= 20 && f.getIdade() > 12
                    && f.getSexo() != f.sexo.MASC) {
                f.imprimeFuncionario();
            }
        }
    }

    private static void imprimir2(List<Funcionario> func, ChecarFunc checagem) {
        for (Funcionario f : func) {
            if (checagem.teste(f)) {
                f.imprimeFuncionario();
            }
        }
    }

}
