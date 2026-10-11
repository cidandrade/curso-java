package reservas.dominio;

/** Identifica o aluno; não pode ser nula nem em branco. */
public record Matricula(String valor) {

    public Matricula {
        if (valor == null || valor.isBlank()) {
            throw new RegraVioladaException("A matrícula do aluno é obrigatória.");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
