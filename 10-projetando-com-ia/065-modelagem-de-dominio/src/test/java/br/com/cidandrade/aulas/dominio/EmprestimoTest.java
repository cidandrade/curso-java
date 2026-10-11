package br.com.cidandrade.aulas.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * As regras do enunciado (MODELAGEM.md), uma por teste
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Empréstimos da biblioteca")
class EmprestimoTest {

    private static final LocalDate HOJE = LocalDate.of(2026, 10, 1);

    private Livro livro;
    private Acervo acervo;
    private Exemplar exemplar;
    private Socio ana;
    private Socio bruno;

    @BeforeEach
    void preparar() {
        livro = new Livro("978-85-359-0277-1", "Dom Casmurro", "Machado de Assis");
        acervo = new Acervo();
        exemplar = acervo.adicionar(livro, "DC-01");
        ana = new Socio("Ana");
        bruno = new Socio("Bruno");
    }

    @Test
    @DisplayName("o prazo de devolução é de 14 dias")
    void prazo() {
        Emprestimo e = ana.emprestar(exemplar, HOJE);

        assertEquals(LocalDate.of(2026, 10, 15), e.getDataPrevista());
    }

    @ParameterizedTest(name = "devolvido {0} dia(s) depois do prazo: R$ {1}")
    @CsvSource({"-5, 0", "0, 0", "1, 1.00", "3, 3.00", "10, 10.00"})
    @DisplayName("a multa é de R$ 1,00 por dia de atraso")
    void multa(int diasAposPrazo, String multaEsperada) {
        Emprestimo e = ana.emprestar(exemplar, HOJE);

        BigDecimal multa = e.devolver(e.getDataPrevista().plusDays(diasAposPrazo));

        assertEquals(0, new BigDecimal(multaEsperada).compareTo(multa));
    }

    @Test
    @DisplayName("um exemplar emprestado não pode ser emprestado a outro sócio")
    void exemplarOcupado() {
        ana.emprestar(exemplar, HOJE);

        assertThrows(IllegalStateException.class, () -> bruno.emprestar(exemplar, HOJE));
        assertTrue(bruno.getEmprestimosAtivos().isEmpty());
    }

    @Test
    @DisplayName("a devolução libera o exemplar")
    void devolucaoLibera() {
        Emprestimo e = ana.emprestar(exemplar, HOJE);

        e.devolver(HOJE.plusDays(2));

        assertTrue(exemplar.isDisponivel());
        assertTrue(ana.getEmprestimosAtivos().isEmpty());
        bruno.emprestar(exemplar, HOJE.plusDays(2));
    }

    @Test
    @DisplayName("um empréstimo não pode ser devolvido duas vezes")
    void devolucaoDupla() {
        Emprestimo e = ana.emprestar(exemplar, HOJE);
        e.devolver(HOJE);

        assertThrows(IllegalStateException.class, () -> e.devolver(HOJE));
    }

    @Test
    @DisplayName("cada sócio pode ter no máximo 3 empréstimos ativos")
    void limite() {
        for (int i = 1; i <= Socio.LIMITE_DE_EMPRESTIMOS; i++) {
            ana.emprestar(acervo.adicionar(livro, "X-" + i), HOJE);
        }
        Exemplar quarto = acervo.adicionar(livro, "X-4");

        assertFalse(ana.podeEmprestar(HOJE));
        assertThrows(IllegalStateException.class, () -> ana.emprestar(quarto, HOJE));
        assertTrue(quarto.isDisponivel());
    }

    @Test
    @DisplayName("quem tem empréstimo atrasado não pode pegar outro")
    void atrasoBloqueia() {
        Emprestimo e = ana.emprestar(exemplar, HOJE);
        LocalDate depoisDoPrazo = e.getDataPrevista().plusDays(1);
        Exemplar outro = acervo.adicionar(livro, "DC-02");

        assertThrows(IllegalStateException.class, () -> ana.emprestar(outro, depoisDoPrazo));
    }

    @Test
    @DisplayName("vários exemplares do mesmo livro podem estar emprestados ao mesmo tempo")
    void variosExemplares() {
        acervo.adicionar(livro, "DC-02");
        assertEquals(2, acervo.disponiveis(livro).size());

        ana.emprestar(acervo.disponiveis(livro).get(0), HOJE);
        bruno.emprestar(acervo.disponiveis(livro).get(0), HOJE);

        assertTrue(acervo.disponiveis(livro).isEmpty());
    }

    @Test
    @DisplayName("livros com o mesmo ISBN são o mesmo livro")
    void identidadeDoLivro() {
        Livro mesmo = new Livro("978-85-359-0277-1", "Dom Casmurro (edição de bolso)", "Machado de Assis");

        assertEquals(livro, mesmo);
        assertEquals(1, acervo.disponiveis(mesmo).size());
    }
}
