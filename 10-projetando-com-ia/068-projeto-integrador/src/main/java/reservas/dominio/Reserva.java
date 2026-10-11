package reservas.dominio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Reserva de uma sala por um aluno. Na criação, garante que o grupo cabe na sala e que o
 * horário ainda não começou; depois, sabe se cancelar. Só é criada pela {@link AgendaDaSala}.
 */
public final class Reserva {

    private static final Duration ANTECEDENCIA_PARA_CANCELAR = Duration.ofHours(1);

    private final ReservaId id;
    private final Sala sala;
    private final Matricula aluno;
    private final Periodo periodo;
    private final int tamanhoGrupo;
    private StatusReserva status;

    Reserva(Sala sala, Matricula aluno, Periodo periodo, int tamanhoGrupo, LocalDateTime agora) {
        this.sala = Objects.requireNonNull(sala, "A sala é obrigatória.");
        this.aluno = Objects.requireNonNull(aluno, "O aluno é obrigatório.");
        this.periodo = Objects.requireNonNull(periodo, "O período é obrigatório.");
        Objects.requireNonNull(agora, "O instante atual é obrigatório.");

        if (tamanhoGrupo < 1) {
            throw new RegraVioladaException("O grupo precisa ter pelo menos uma pessoa.");
        }
        if (!sala.comporta(tamanhoGrupo)) {
            throw new RegraVioladaException(String.format(
                    "A sala %d comporta no máximo %d pessoas; o grupo tem %d.",
                    sala.numero(), sala.capacidade(), tamanhoGrupo));
        }
        if (periodo.jaComecouEm(agora)) {
            throw new RegraVioladaException("Não é possível reservar um horário que já começou (" + periodo + ").");
        }

        this.id = ReservaId.nova();
        this.tamanhoGrupo = tamanhoGrupo;
        this.status = StatusReserva.ATIVA;
    }

    public ReservaId id() {
        return id;
    }

    public Sala sala() {
        return sala;
    }

    public Matricula aluno() {
        return aluno;
    }

    public Periodo periodo() {
        return periodo;
    }

    public int tamanhoGrupo() {
        return tamanhoGrupo;
    }

    public StatusReserva status() {
        return status;
    }

    public boolean estaAtiva() {
        return status == StatusReserva.ATIVA;
    }

    public boolean pertenceA(Matricula matricula) {
        return aluno.equals(matricula);
    }

    /** Uma reserva ocupa o período se estiver ativa e se os horários se sobrepuserem. */
    public boolean ocupa(Periodo outro) {
        return estaAtiva() && periodo.sobrepoe(outro);
    }

    /** Horas que a reserva consome da cota do aluno; uma reserva cancelada não consome nada. */
    public int horas() {
        return estaAtiva() ? periodo.horas() : 0;
    }

    /** Cancela a reserva se o solicitante for o dono e se faltar pelo menos 1 hora para o início. */
    public void cancelar(Matricula solicitante, LocalDateTime agora) {
        if (!pertenceA(solicitante)) {
            throw new RegraVioladaException("Só o aluno que fez a reserva pode cancelá-la.");
        }
        if (!estaAtiva()) {
            throw new RegraVioladaException("Esta reserva já foi cancelada.");
        }
        LocalDateTime limite = periodo.inicio().minus(ANTECEDENCIA_PARA_CANCELAR);
        if (agora.isAfter(limite)) {
            throw new RegraVioladaException(
                    "O cancelamento só é permitido até " + limite.toLocalTime() + " (1 hora antes do início).");
        }
        status = StatusReserva.CANCELADA;
    }

    @Override
    public boolean equals(Object outro) {
        return outro instanceof Reserva reserva && reserva.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Reserva %s: sala %d, aluno %s, %s, %d pessoa(s), %s",
                id, sala.numero(), aluno, periodo, tamanhoGrupo, status);
    }
}
