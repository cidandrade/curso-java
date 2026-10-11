package reservas.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

/** O que o aluno procura: um período, uma capacidade mínima e, opcionalmente, projetor. */
public record CriterioDeBusca(Periodo periodo, int capacidadeMinima, boolean exigeProjetor) {

    public CriterioDeBusca {
        Objects.requireNonNull(periodo, "O período da busca é obrigatório.");
        if (capacidadeMinima < 1) {
            throw new RegraVioladaException("A capacidade mínima da busca deve ser de pelo menos 1 pessoa.");
        }
    }

    /** Diz se a sala tem os requisitos fixos da busca (lugares e projetor), sem olhar a agenda. */
    public boolean aceita(Sala sala) {
        return sala.comporta(capacidadeMinima) && (!exigeProjetor || sala.recursos().projetor());
    }

    /** Uma busca só faz sentido para um período que ainda não começou. */
    public boolean podeSerAtendidoEm(LocalDateTime agora) {
        return !periodo.jaComecouEm(agora);
    }
}
