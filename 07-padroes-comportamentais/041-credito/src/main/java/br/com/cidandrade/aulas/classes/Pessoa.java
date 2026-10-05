package br.com.cidandrade.aulas.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pessoa {

    private String nome, cpf;
    private double salario;
    private boolean temCarro, temImovel;
}
