package reservas.aplicacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.ReservaId;
import reservas.dominio.Sala;
import reservas.infra.RepositorioDeReservasEmMemoria;

@DisplayName("CentralDeReservas: pontos que estavam em aberto")
class CentralDeReservasLacunasTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDate DIA = LocalDate.of(2026, 3, 10);
    private static final Matricula ANA = new Matricula("RA001");

    private RepositorioDeReservas repositorio;
    private CatalogoDeSalas catalogo;

    @BeforeEach
    void preparar() {
        repositorio = new RepositorioDeReservasEmMemoria();
        catalogo = new CatalogoDeSalas();
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));
    }

    private CentralDeReservas centralEm(LocalDateTime agora) {
        Clock relogio = Clock.fixed(agora.atZone(FUSO).toInstant(), FUSO);
        return new CentralDeReservas(relogio, repositorio, catalogo);
    }

    @Test
    @DisplayName("Regra acrescentada: não se reserva numa sala que não existe")
    void recusaSalaInexistente() {
        CentralDeReservas central = centralEm(DIA.atTime(7, 0));

        assertThrows(RegraVioladaException.class,
                () -> central.reservar(ANA, 999, new Periodo(DIA, 10, 1), 1));
        assertTrue(repositorio.ativasDoAluno(ANA, DIA).isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: não se cancela uma reserva que não existe")
    void recusaCancelarReservaInexistente() {
        CentralDeReservas central = centralEm(DIA.atTime(7, 0));

        assertThrows(RegraVioladaException.class,
                () -> central.cancelar(new ReservaId(UUID.randomUUID()), ANA));
    }

    @ParameterizedTest(name = "[{index}] às 15h, buscar o horário das {0}h")
    @ValueSource(ints = {8, 14, 15})
    @DisplayName("Regra acrescentada: buscar salas num horário que já começou devolve lista vazia")
    void buscaEmHorarioIniciadoDevolveVazio(int hora) {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(new CriterioDeBusca(new Periodo(DIA, hora, 1), 1, false));

        assertTrue(livres.isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: buscar salas num dia que já passou devolve lista vazia")
    void buscaEmDiaAnteriorDevolveVazio() {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(
                new CriterioDeBusca(new Periodo(DIA.minusDays(1), 16, 1), 1, false));

        assertTrue(livres.isEmpty());
    }

    @Test
    @DisplayName("Regra acrescentada: um horário que ainda não começou continua aparecendo na busca")
    void buscaEmHorarioFuturoContinuaFuncionando() {
        CentralDeReservas central = centralEm(DIA.atTime(15, 0));

        List<Sala> livres = central.buscarSalasLivres(new CriterioDeBusca(new Periodo(DIA, 16, 1), 1, false));

        assertEquals(Set.of(101, 102), livres.stream().map(Sala::numero).collect(Collectors.toSet()));
    }
}
