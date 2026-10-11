package reservas.infra;

import org.junit.jupiter.api.DisplayName;
import reservas.dominio.RepositorioDeReservas;

@DisplayName("RepositorioDeReservasEmMemoria")
class RepositorioDeReservasEmMemoriaTest extends ContratoDoRepositorioDeReservas {

    @Override
    protected RepositorioDeReservas novoRepositorio() {
        return new RepositorioDeReservasEmMemoria();
    }
}
