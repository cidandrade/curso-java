package reservas.dominio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** Conjunto das salas da faculdade, sem números repetidos. */
public final class CatalogoDeSalas {

    private final Map<Integer, Sala> salas = new LinkedHashMap<>();

    public void cadastrar(Sala sala) {
        Objects.requireNonNull(sala, "A sala é obrigatória.");
        if (salas.containsKey(sala.numero())) {
            throw new RegraVioladaException("Já existe uma sala com o número " + sala.numero() + ".");
        }
        salas.put(sala.numero(), sala);
    }

    public Optional<Sala> buscar(int numero) {
        return Optional.ofNullable(salas.get(numero));
    }

    /** Salas que atendem aos requisitos fixos do critério, sem considerar a disponibilidade. */
    public List<Sala> candidatas(CriterioDeBusca criterio) {
        return salas.values().stream().filter(criterio::aceita).toList();
    }
}
