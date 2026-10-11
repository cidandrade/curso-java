package reservas.dominio;

import java.util.Objects;
import java.util.UUID;

/** Identificador único de uma reserva. */
public record ReservaId(UUID valor) {

    public ReservaId {
        Objects.requireNonNull(valor, "O identificador da reserva é obrigatório.");
    }

    public static ReservaId nova() {
        return new ReservaId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return valor.toString();
    }
}
