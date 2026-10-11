package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.dominio.Acervo;
import br.com.cidandrade.aulas.dominio.Emprestimo;
import br.com.cidandrade.aulas.dominio.Exemplar;
import br.com.cidandrade.aulas.dominio.Livro;
import br.com.cidandrade.aulas.dominio.Socio;
import java.time.LocalDate;

/**
 * Aplicação: um empréstimo devolvido com atraso. A análise que levou a
 * estas classes está em MODELAGEM.md
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class BibliotecaApp {

    public static void main(String[] args) {
        Livro domCasmurro = new Livro("978-85-359-0277-1", "Dom Casmurro", "Machado de Assis");
        Acervo acervo = new Acervo();
        acervo.adicionar(domCasmurro, "DC-01");
        acervo.adicionar(domCasmurro, "DC-02");

        Socio ana = new Socio("Ana");
        LocalDate hoje = LocalDate.of(2026, 10, 1);
        Exemplar exemplar = acervo.disponiveis(domCasmurro).get(0);
        Emprestimo emprestimo = ana.emprestar(exemplar, hoje);

        System.out.println(ana.getNome() + " pegou " + exemplar);
        System.out.println("Devolver até " + emprestimo.getDataPrevista());
        System.out.println("Exemplares ainda disponíveis: "
                + acervo.disponiveis(domCasmurro).size());

        LocalDate devolucao = emprestimo.getDataPrevista().plusDays(3);
        System.out.println("Devolvido em " + devolucao
                + ", multa de R$ " + emprestimo.devolver(devolucao));
    }
}
