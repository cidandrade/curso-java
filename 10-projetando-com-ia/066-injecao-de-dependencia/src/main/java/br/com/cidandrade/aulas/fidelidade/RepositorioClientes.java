package br.com.cidandrade.aulas.fidelidade;

import java.util.Optional;

/**
 * Onde os clientes ficam guardados. O ProgramaFidelidade só conhece esta
 * interface; se é memória, arquivo ou banco, quem decide é a composition
 * root (o main)
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public interface RepositorioClientes {

    Optional<Cliente> buscar(String cpf);

    void salvar(Cliente cliente);
}
