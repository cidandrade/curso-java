package br.com.cidandrade.aulas.pagamento;

/**
 * Operações sobre pagamentos que não pertencem a cada tipo, como a
 * descrição para o recibo
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public final class Pagamentos {

    private Pagamentos() {
    }

    /**
     * instanceof com pattern matching (Java 16): testa o tipo e já declara
     * a variável convertida (c, pix, d), sem cast.
     *
     * No Java 21, o mesmo método pode ser escrito com switch e padrões de
     * tipo. Como Pagamento é selado, o compilador confere que todos os
     * tipos foram tratados, sem precisar de default:
     *
     * <pre>
     * return switch (p) {
     *     case Cartao c when c.parcelas() > 1 -> "Cartão " + c.bandeira() + " em " + c.parcelas() + "x";
     *     case Cartao c -> "Cartão " + c.bandeira() + " à vista";
     *     case Pix pix -> "Pix (" + pix.chave() + ")";
     *     case Dinheiro d -> "Dinheiro (recebido R$ " + d.recebido() + ")";
     * };
     * </pre>
     *
     * Este curso compila para Java 17, em que esse switch não existe
     * (era só um recurso em prévia). Se a IA gerar esse código para um
     * projeto Java 17, ele não compila.
     *
     * @param p pagamento
     * @return descrição para o recibo
     */
    public static String descrever(Pagamento p) {
        if (p instanceof Cartao c) {
            return "Cartão " + c.bandeira()
                    + (c.parcelas() > 1 ? " em " + c.parcelas() + "x" : " à vista");
        }
        if (p instanceof Pix pix) {
            return "Pix (" + pix.chave() + ")";
        }
        if (p instanceof Dinheiro d) {
            return "Dinheiro (recebido R$ " + d.recebido() + ")";
        }
        throw new IllegalArgumentException("Pagamento desconhecido: " + p);
    }
}
