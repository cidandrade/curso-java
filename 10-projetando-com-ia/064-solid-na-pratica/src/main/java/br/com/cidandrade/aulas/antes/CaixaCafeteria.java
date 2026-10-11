package br.com.cidandrade.aulas.antes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ANTES: o tipo de código que uma IA costuma gerar a partir de um pedido
 * vago ("faça o caixa de uma cafeteria"). Funciona, mas fere os cinco
 * princípios SOLID. Compare com o pacote depois
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class CaixaCafeteria {

    private final List<String> nomes = new ArrayList<>();
    private final List<BigDecimal> subtotais = new ArrayList<>();

    // [D] Depende de uma classe concreta, criada aqui dentro: não dá para
    // trocá-la nem testar o caixa sem disparar o envio
    private final EmailService email = new EmailService();

    public void adicionar(String nome, BigDecimal preco, int quantidade) {
        nomes.add(nome);
        subtotais.add(preco.multiply(BigDecimal.valueOf(quantidade)));
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal s : subtotais) {
            total = total.add(s);
        }
        return total;
    }

    // [S] Esta classe calcula, cobra, monta o recibo, imprime e notifica:
    // são cinco motivos diferentes para alterá-la
    // [O] Cada forma de pagamento nova obriga a mexer neste switch
    public void pagar(String forma) {
        switch (forma) {
            case "cartao":
                System.out.println("Cobrando R$ " + total() + " no cartão");
                break;
            case "pix":
                System.out.println("Gerando Pix de R$ " + total());
                break;
            case "vale":
                System.out.println("Debitando R$ " + total() + " do vale-refeição");
                break;
            default:
                throw new IllegalArgumentException("Forma desconhecida: " + forma);
        }
        imprimirRecibo();
        email.enviar("cliente@exemplo.com", "Pagamento de R$ " + total() + " recebido");
    }

    // [L] e [I] Toda forma de pagamento "sabe" estornar, mas o vale não:
    // quem chama precisa saber que, para o vale, o método explode
    public void estornar(String forma) {
        if (forma.equals("vale")) {
            throw new UnsupportedOperationException("Vale-refeição não tem estorno");
        }
        System.out.println("Estornando R$ " + total() + " (" + forma + ")");
    }

    // [S] Formatação e saída misturadas com regra de negócio
    private void imprimirRecibo() {
        System.out.println("----- RECIBO -----");
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println(nomes.get(i) + ": R$ " + subtotais.get(i));
        }
        System.out.println("Total: R$ " + total());
    }

    /**
     * Serviço de e-mail "de verdade" (aqui, só imprime)
     */
    static class EmailService {

        void enviar(String para, String mensagem) {
            System.out.println("[e-mail para " + para + "] " + mensagem);
        }
    }
}
