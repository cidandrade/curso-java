package reservas;

import java.time.Clock;
import java.time.LocalDate;
import reservas.aplicacao.CentralDeReservas;
import reservas.dominio.CatalogoDeSalas;
import reservas.dominio.CriterioDeBusca;
import reservas.dominio.Matricula;
import reservas.dominio.Periodo;
import reservas.dominio.Recursos;
import reservas.dominio.RegraVioladaException;
import reservas.dominio.RepositorioDeReservas;
import reservas.dominio.Reserva;
import reservas.dominio.Sala;
import reservas.infra.RepositorioDeReservasEmMemoria;

/** Composition root: monta as dependências e mostra um cenário curto no console. */
public final class ReservasApp {

    private ReservasApp() {
    }

    public static void main(String[] args) {
        Clock relogio = Clock.systemDefaultZone();

        CatalogoDeSalas catalogo = new CatalogoDeSalas();
        catalogo.cadastrar(new Sala(101, 4, new Recursos(false, false)));
        catalogo.cadastrar(new Sala(102, 8, new Recursos(true, false)));
        catalogo.cadastrar(new Sala(103, 10, new Recursos(false, true)));
        catalogo.cadastrar(new Sala(104, 6, new Recursos(true, true)));

        RepositorioDeReservas repositorio = new RepositorioDeReservasEmMemoria();
        CentralDeReservas central = new CentralDeReservas(relogio, repositorio, catalogo);

        // Amanhã inteiro está no futuro, seja qual for a hora em que o programa rodar.
        LocalDate amanha = LocalDate.now(relogio).plusDays(1);
        Matricula ana = new Matricula("RA001");

        System.out.println("== Reservas de salas para " + amanha + " ==");

        Reserva reserva = central.reservar(ana, 102, new Periodo(amanha, 10, 2), 5);
        System.out.println("1. Reserva feita: " + reserva);

        System.out.println("2. Ana tenta reservar mais 1h na sala 101, às 14h:");
        try {
            central.reservar(ana, 101, new Periodo(amanha, 14, 1), 2);
            System.out.println("   Reserva aceita (não deveria acontecer).");
        } catch (RegraVioladaException e) {
            System.out.println("   Recusada: " + e.getMessage());
        }

        System.out.println("3. Salas livres amanhã às 10h, para 4 pessoas, com projetor:");
        central.buscarSalasLivres(new CriterioDeBusca(new Periodo(amanha, 10, 1), 4, true))
                .forEach(sala -> System.out.println("   " + sala));
    }
}
