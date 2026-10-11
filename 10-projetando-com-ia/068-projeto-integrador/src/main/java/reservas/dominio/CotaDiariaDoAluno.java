package reservas.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Horas que um aluno já reservou num dia; recusa o que passaria do limite diário de 2 horas. */
public final class CotaDiariaDoAluno {

    public static final int LIMITE_HORAS = 2;

    private final Matricula aluno;
    private final LocalDate data;
    private final List<Reserva> reservas;

    public CotaDiariaDoAluno(Matricula aluno, LocalDate data, List<Reserva> reservas) {
        this.aluno = Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        this.data = Objects.requireNonNull(data, "A data é obrigatória.");
        this.reservas = new ArrayList<>();
        Objects.requireNonNull(reservas, "A lista de reservas é obrigatória.").forEach(this::registrar);
    }

    public Matricula aluno() {
        return aluno;
    }

    public LocalDate data() {
        return data;
    }

    public int horasUsadas() {
        return reservas.stream().mapToInt(Reserva::horas).sum();
    }

    public void garantirQueCabe(Periodo periodo) {
        if (!periodo.data().equals(data)) {
            throw new IllegalArgumentException("O período " + periodo + " não é do dia desta cota (" + data + ").");
        }
        if (horasUsadas() + periodo.horas() > LIMITE_HORAS) {
            throw new RegraVioladaException(String.format(
                    "O aluno %s já tem %dh reservadas em %s; o limite é de %dh por dia.",
                    aluno, horasUsadas(), data, LIMITE_HORAS));
        }
    }

    /** Usado pela agenda logo depois de criar uma reserva, para que a mesma cota acumule as horas. */
    void registrar(Reserva reserva) {
        if (!reserva.pertenceA(aluno) || !reserva.periodo().data().equals(data)) {
            throw new IllegalArgumentException("A reserva " + reserva + " não é do aluno e do dia desta cota.");
        }
        reservas.add(reserva);
    }
}
