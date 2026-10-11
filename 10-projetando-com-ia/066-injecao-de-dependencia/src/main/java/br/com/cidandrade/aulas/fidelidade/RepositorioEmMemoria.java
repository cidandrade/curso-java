package br.com.cidandrade.aulas.fidelidade;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repositório que guarda os clientes num mapa, enquanto o programa roda
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class RepositorioEmMemoria implements RepositorioClientes {

    private final Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public Optional<Cliente> buscar(String cpf) {
        return Optional.ofNullable(clientes.get(cpf));
    }

    @Override
    public void salvar(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }
}
