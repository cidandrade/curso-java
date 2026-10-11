package reservas.infra;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import reservas.dominio.Matricula;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;

/**
 * Implementação de {@link RepositorioDeReservas} que guarda as reservas num mapa em memória.
 *
 * <p>Guarda referências, não cópias, como um identity map: uma alteração feita num objeto já
 * salvo aparece aqui mesmo sem nova chamada a {@code salvar}. Quem usa o repositório não pode
 * contar com isso; ele deve sempre chamar {@code salvar} depois de alterar uma reserva, como
 * exigem {@code ContratoDoRepositorioDeReservas} e {@code CentralDeReservasGravacaoTest}.
 */
public final class RepositorioDeReservasEmMemoria implements RepositorioDeReservas {

    private final Map<ReservaId, Reserva> dados = new LinkedHashMap<>();

    @Override
    public void salvar(Reserva reserva) {
        Objects.requireNonNull(reserva, "A reserva é obrigatória.");
        dados.put(reserva.id(), reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(ReservaId id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<Reserva> ativasDaSala(int numeroSala, LocalDate data) {
        return dados.values().stream()
                .filter(Reserva::estaAtiva)
                .filter(reserva -> reserva.sala().numero() == numeroSala)
                .filter(reserva -> reserva.periodo().data().equals(data))
                .toList();
    }

    @Override
    public List<Reserva> ativasDoAluno(Matricula aluno, LocalDate data) {
        return dados.values().stream()
                .filter(Reserva::estaAtiva)
                .filter(reserva -> reserva.pertenceA(aluno))
                .filter(reserva -> reserva.periodo().data().equals(data))
                .toList();
    }
}
