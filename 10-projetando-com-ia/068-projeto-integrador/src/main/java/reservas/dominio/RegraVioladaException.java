package reservas.dominio;

/** Sinaliza que uma regra de negócio foi violada; a mensagem é legível para o aluno. */
public final class RegraVioladaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RegraVioladaException(String mensagem) {
        super(mensagem);
    }
}
