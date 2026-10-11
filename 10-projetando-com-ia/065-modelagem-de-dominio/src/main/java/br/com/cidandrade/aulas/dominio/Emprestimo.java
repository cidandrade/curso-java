package br.com.cidandrade.aulas.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * O empréstimo de um exemplar a um sócio. Sabe o próprio prazo, se está
 * atrasado e quanto de multa cobrar. A data de hoje chega sempre como
 * parâmetro: assim os testes controlam o tempo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Emprestimo {

    public static final int PRAZO_EM_DIAS = 14;
    public static final BigDecimal MULTA_POR_DIA = new BigDecimal("1.00");

    private final Socio socio;
    private final Exemplar exemplar;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    // Criado só por Socio.emprestar, que confere as regras antes
    Emprestimo(Socio socio, Exemplar exemplar, LocalDate dataEmprestimo) {
        this.socio = socio;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        exemplar.emprestar(this);
    }

    public LocalDate getDataPrevista() {
        return dataEmprestimo.plusDays(PRAZO_EM_DIAS);
    }

    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    public boolean isAtrasado(LocalDate hoje) {
        return isAtivo() && hoje.isAfter(getDataPrevista());
    }

    /**
     * @param data data de referência
     * @return multa pelos dias de atraso até a data; zero se no prazo
     */
    public BigDecimal multa(LocalDate data) {
        long diasDeAtraso = ChronoUnit.DAYS.between(getDataPrevista(), data);
        return diasDeAtraso > 0
                ? MULTA_POR_DIA.multiply(BigDecimal.valueOf(diasDeAtraso))
                : BigDecimal.ZERO;
    }

    /**
     * Registra a devolução e libera o exemplar
     *
     * @param hoje data da devolução
     * @return multa a pagar
     * @throws IllegalStateException se o empréstimo já tiver sido devolvido
     */
    public BigDecimal devolver(LocalDate hoje) {
        if (!isAtivo()) {
            throw new IllegalStateException("Este empréstimo já foi devolvido");
        }
        dataDevolucao = hoje;
        exemplar.devolver();
        socio.encerrar(this);
        return multa(hoje);
    }

    public Socio getSocio() {
        return socio;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }
}
