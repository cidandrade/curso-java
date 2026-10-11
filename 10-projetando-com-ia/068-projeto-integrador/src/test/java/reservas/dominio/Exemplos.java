package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/** Dados e atalhos compartilhados pelos testes de domínio. */
final class Exemplos {

    static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    /** Um instante anterior a qualquer horário de DIA. */
    static final LocalDateTime BEM_ANTES = LocalDateTime.of(2026, 3, 9, 12, 0);

    static final Matricula ANA = new Matricula("RA001");
    static final Matricula BETO = new Matricula("RA002");

    private Exemplos() {
    }

    static Sala sala(int numero, int capacidade) {
        return new Sala(numero, capacidade, new Recursos(false, false));
    }

    static Periodo periodo(int hora, int duracao) {
        return new Periodo(DIA, hora, duracao);
    }

    static AgendaDaSala agendaVazia(Sala sala) {
        return new AgendaDaSala(sala, DIA, List.of());
    }

    static CotaDiariaDoAluno cotaVazia(Matricula aluno) {
        return new CotaDiariaDoAluno(aluno, DIA, List.of());
    }

    /** Cria uma reserva válida pelo único caminho público: a agenda da sala. */
    static Reserva reservaDe(Matricula aluno, Sala sala, int hora, int duracao) {
        return agendaVazia(sala).reservar(aluno, periodo(hora, duracao), 1, cotaVazia(aluno), BEM_ANTES);
    }
}
