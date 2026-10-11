package reservas.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Armazenamento das reservas; o domínio não sabe se é memória, arquivo ou banco. */
public interface RepositorioDeReservas {

    void salvar(Reserva reserva);

    Optional<Reserva> buscarPorId(ReservaId id);

    List<Reserva> ativasDaSala(int numeroSala, LocalDate data);

    List<Reserva> ativasDoAluno(Matricula aluno, LocalDate data);
}
