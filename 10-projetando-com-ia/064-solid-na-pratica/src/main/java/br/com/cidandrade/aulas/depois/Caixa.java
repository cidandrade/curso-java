package br.com.cidandrade.aulas.depois;

/**
 * Coordena o fechamento de um pedido. Cada tarefa é delegada a quem
 * é responsável por ela
 * [O] Aceita qualquer FormaPagamento, inclusive as que ainda não existem
 * [D] Recebe o Notificador pelo construtor, em vez de criá-lo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class Caixa {

    private final Notificador notificador;
    // GeradorRecibo não acessa nada externo (rede, arquivo, relógio),
    // então criá-lo aqui não atrapalha os testes
    private final GeradorRecibo gerador = new GeradorRecibo();

    public Caixa(Notificador notificador) {
        this.notificador = notificador;
    }

    /**
     * Cobra o pedido, notifica o cliente e devolve o recibo
     *
     * @param pedido pedido a fechar
     * @param forma forma de pagamento escolhida
     * @return texto do recibo, com o comprovante
     */
    public String fechar(Pedido pedido, FormaPagamento forma) {
        String comprovante = forma.pagar(pedido.total());
        notificador.notificar(comprovante);
        return gerador.gerar(pedido) + "\n" + comprovante;
    }

    /**
     * Estorna o pedido. Só aceita formas de pagamento estornáveis: tentar
     * estornar um Vale nem compila
     *
     * @param pedido pedido a estornar
     * @param forma forma de pagamento usada
     * @return texto do comprovante de estorno
     */
    public String estornar(Pedido pedido, Estornavel forma) {
        String comprovante = forma.estornar(pedido.total());
        notificador.notificar(comprovante);
        return comprovante;
    }
}
