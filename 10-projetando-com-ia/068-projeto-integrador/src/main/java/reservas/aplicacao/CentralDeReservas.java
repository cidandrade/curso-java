package reservas.aplicacao;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import reservas.dominio.AgendaDaSala;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CotaDiariaDoAluno;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;

/**
 * Ponto de entrada dos casos de uso. Obtém o "agora" do relógio, carrega e salva dados e
 * delega todas as decisões de negócio aos objetos de domínio.
 */
public final class CentralDeReservas {

    private final Clock relogio;
    private final RepositorioDeReservas repositorio;
    private final CatalogoDeSalas catalogo;

    public CentralDeReservas(Clock relogio, RepositorioDeReservas repositorio, CatalogoDeSalas catalogo) {
        this.relogio = Objects.requireNonNull(relogio, "O relógio é obrigatório.");
        this.repositorio = Objects.requireNonNull(repositorio, "O repositório é obrigatório.");
        this.catalogo = Objects.requireNonNull(catalogo, "O catálogo é obrigatório.");
    }

    public Reserva reservar(Matricula aluno, int numeroSala, Periodo periodo, int tamanhoGrupo) {
        Sala sala = catalogo.buscar(numeroSala)
                .orElseThrow(() -> new RegraVioladaException("Não existe sala com o número " + numeroSala + "."));
        LocalDate dia = periodo.data();
        CotaDiariaDoAluno cota = new CotaDiariaDoAluno(aluno, dia, repositorio.ativasDoAluno(aluno, dia));

        Reserva reserva = agendaDe(sala, dia).reservar(aluno, periodo, tamanhoGrupo, cota, agora());

        repositorio.salvar(reserva);
        return reserva;
    }

    public void cancelar(ReservaId id, Matricula solicitante) {
        Reserva reserva = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RegraVioladaException("Reserva não encontrada: " + id + "."));

        reserva.cancelar(solicitante, agora());

        repositorio.salvar(reserva);
    }

    public List<Sala> buscarSalasLivres(CriterioDeBusca criterio) {
        if (!criterio.podeSerAtendidoEm(agora())) {
            return List.of();
        }
        Periodo periodo = criterio.periodo();
        return catalogo.candidatas(criterio).stream()
                .filter(sala -> agendaDe(sala, periodo.data()).estaLivre(periodo))
                .toList();
    }

    private AgendaDaSala agendaDe(Sala sala, LocalDate dia) {
        return new AgendaDaSala(sala, dia, repositorio.ativasDaSala(sala.numero(), dia));
    }

    private LocalDateTime agora() {
        return LocalDateTime.now(relogio);
    }
}
