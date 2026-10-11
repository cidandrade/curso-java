package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Reservas de uma sala num dia. Garante que não haja dois horários sobrepostos e é o único
 * caminho para criar uma {@link Reserva}, sempre passando pela cota diária do aluno.
 */
public final class AgendaDaSala {

    private final Sala sala;
    private final LocalDate data;
    private final List<Reserva> reservas;

    public AgendaDaSala(Sala sala, LocalDate data, List<Reserva> reservas) {
        this.sala = Objects.requireNonNull(sala, "A sala é obrigatória.");
        this.data = Objects.requireNonNull(data, "A data é obrigatória.");
        this.reservas = new ArrayList<>(Objects.requireNonNull(reservas, "A lista de reservas é obrigatória."));
        for (Reserva reserva : this.reservas) {
            if (!reserva.sala().equals(sala) || !reserva.periodo().data().equals(data)) {
                throw new IllegalArgumentException("A reserva " + reserva + " não pertence a esta agenda.");
            }
        }
    }

    public boolean estaLivre(Periodo periodo) {
        exigirMesmoDia(periodo);
        return reservas.stream().noneMatch(reserva -> reserva.ocupa(periodo));
    }

    public Reserva reservar(Matricula aluno, Periodo periodo, int tamanhoGrupo,
                            CotaDiariaDoAluno cota, LocalDateTime agora) {
        Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        Objects.requireNonNull(cota, "A cota do aluno é obrigatória.");
        Objects.requireNonNull(agora, "O instante atual é obrigatório.");
        exigirMesmoDia(periodo);
        if (!cota.aluno().equals(aluno) || !cota.data().equals(data)) {
            throw new IllegalArgumentException("A cota informada não é do aluno e do dia desta reserva.");
        }

        cota.garantirQueCabe(periodo);
        if (!estaLivre(periodo)) {
            throw new RegraVioladaException(String.format(
                    "A sala %d já está reservada em parte do horário %s.", sala.numero(), periodo));
        }
        Reserva nova = new Reserva(sala, aluno, periodo, tamanhoGrupo, agora);

        reservas.add(nova);
        cota.registrar(nova);
        return nova;
    }

    private void exigirMesmoDia(Periodo periodo) {
        Objects.requireNonNull(periodo, "O período é obrigatório.");
        if (!periodo.data().equals(data)) {
            throw new IllegalArgumentException("O período " + periodo + " não é do dia desta agenda (" + data + ").");
        }
    }
}
