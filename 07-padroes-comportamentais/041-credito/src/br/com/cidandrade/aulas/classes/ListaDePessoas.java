package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.util.Matematica;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ListaDePessoas {

    private final List<Pessoa> lista;
    private static ListaDePessoas listaDePessoas;
    private final int SALARIO_MAXIMO = 20_000;
    private final float INDICE_CARRO = 0.8F;
    private final float INDICE_IMOVEL = 0.25F;

    public Pessoa getPessoaPorCPF(String cpf) {
        Pessoa p = null;
        for (Pessoa ps : lista) {
            if (ps.getCpf().equals(cpf)) {
                p = ps;
            }
        }
        return p;
    }

    // Singleton
    public static ListaDePessoas obterPessoas() {
        if (listaDePessoas == null) {
            listaDePessoas = new ListaDePessoas();
        }
        return listaDePessoas;
    }

    private ListaDePessoas() {
        double salario;
        lista = new ArrayList<>();

        salario = estimaSalario();
        lista.add(new Pessoa("Amábile", "012.345.678-90",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Abgail", "123.456.789-00",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Bibiana", "234.567.890-01",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Caetana", "345.678.900-12",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Domitila", "456.789.001-23",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Rosália", "567.890.012-34",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Álvaro", "678.900.123-45",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Ari", "789.001.234-56",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Bartolomeu", "890.012.345-67",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Horácio", "900.123.456-78",
                salario, temCarro(salario),
                temImovel(salario)));
        salario = estimaSalario();
        lista.add(new Pessoa("Moacir", "001.234.567-89",
                salario, temCarro(salario),
                temImovel(salario)));
    }

    private double estimaSalario() {
        return (int) Matematica.aleatorioEmFaixa(
                2, SALARIO_MAXIMO / 2_000) * 500;
    }

    private boolean temCarro(double salario) {
        return (salario >= 1_500
                ? Matematica.aleatorio() < INDICE_CARRO
                : false);
    }

    private boolean temImovel(double salario) {
        return (salario >= 4_500
                ? Matematica.aleatorio() < INDICE_IMOVEL
                : false);
    }
}
