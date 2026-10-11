package reservas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Intervalo de horas cheias em um dia, dentro do horário de funcionamento (8h às 22h),
 * com duração de 1 a 2 horas. O fim é exclusivo.
 */
public record Periodo(LocalDate data, int horaInicio, int duracaoHoras) {

    public static final int ABERTURA = 8;
    public static final int FECHAMENTO = 22;
    public static final int DURACAO_MINIMA = 1;
    public static final int DURACAO_MAXIMA = 2;

    public Periodo {
        Objects.requireNonNull(data, "A data do período é obrigatória.");
        if (duracaoHoras < DURACAO_MINIMA || duracaoHoras > DURACAO_MAXIMA) {
            throw new RegraVioladaException(String.format(
                    "A reserva deve durar de %d a %d horas; foi pedido %dh.",
                    DURACAO_MINIMA, DURACAO_MAXIMA, duracaoHoras));
        }
        if (horaInicio < ABERTURA || horaInicio > FECHAMENTO - duracaoHoras) {
            throw new RegraVioladaException(String.format(
                    "As salas funcionam das %dh às %dh; o período %dh–%dh está fora desse horário.",
                    ABERTURA, FECHAMENTO, horaInicio, horaInicio + duracaoHoras));
        }
    }

    public LocalDateTime inicio() {
        return data.atTime(horaInicio, 0);
    }

    public LocalDateTime fim() {
        return inicio().plusHours(duracaoHoras);
    }

    public int horas() {
        return duracaoHoras;
    }

    public boolean sobrepoe(Periodo outro) {
        return inicio().isBefore(outro.fim()) && outro.inicio().isBefore(fim());
    }

    public boolean jaComecouEm(LocalDateTime agora) {
        return !agora.isBefore(inicio());
    }

    @Override
    public String toString() {
        return String.format("%s, %dh–%dh", data, horaInicio, horaInicio + duracaoHoras);
    }
}
