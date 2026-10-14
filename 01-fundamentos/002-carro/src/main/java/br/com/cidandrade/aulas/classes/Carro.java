package br.com.cidandrade.aulas.classes;

/**
 * Esta classe representa um carro. O próprio objeto garante que o seu
 * estado é sempre válido:
 * <ul>
 * <li>a marcha vai de -1 (ré) a 5, e 0 é o ponto morto;</li>
 * <li>a velocidade nunca é negativa: ela é o módulo, e o sentido vem da
 * marcha;</li>
 * <li>em ponto morto, o carro não acelera;</li>
 * <li>com o carro andando, não se entra na ré nem se sai dela (nem
 * para o ponto morto, que não guarda o sentido do movimento).</li>
 * </ul>
 * Argumento inválido gera {@link IllegalArgumentException}; argumento
 * válido, mas proibido no estado atual, gera {@link IllegalStateException}
 *
 * @author profandrade@gmail.com
 * @version 2.0.0
 */
public class Carro {

    private static final int RE = -1;
    private static final int PONTO_MORTO = 0;
    private static final int MARCHA_MAXIMA = 5;

    private int velocidade = 0;
    private int marcha = PONTO_MORTO;

    /**
     * Troca a marcha do carro
     *
     * @param novaMarcha marcha que será engatada: -1 (ré), 0 (ponto morto)
     * ou de 1 a 5
     * @throws IllegalArgumentException se a marcha estiver fora dessa faixa
     * @throws IllegalStateException se o carro estiver andando e a troca
     * entrar na ré ou sair dela
     */
    public void mudarMarcha(int novaMarcha) {
        if (novaMarcha < RE || novaMarcha > MARCHA_MAXIMA) {
            throw new IllegalArgumentException("Marcha inválida: " + novaMarcha
                    + " (use de " + RE + " a " + MARCHA_MAXIMA + ")");
        }
        boolean entraOuSaiDaRe = (novaMarcha == RE) != (marcha == RE);
        if (velocidade > 0 && entraOuSaiDaRe) {
            throw new IllegalStateException("Não é possível passar da marcha "
                    + marcha + " para a " + novaMarcha + " com o carro a "
                    + velocidade + " km/h: pare o carro antes");
        }
        marcha = novaMarcha;
    }

    /**
     * Acelera o carro
     *
     * @param incremento quanto será somado à velocidade, em km/h
     * @throws IllegalArgumentException se o incremento for negativo
     * @throws IllegalStateException se o carro estiver em ponto morto
     */
    public void aumentarVelocidade(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("Incremento negativo: " + incremento
                    + " (para reduzir a velocidade, use frear)");
        }
        if (marcha == PONTO_MORTO) {
            throw new IllegalStateException(
                    "O carro está em ponto morto: engate uma marcha antes de acelerar");
        }
        velocidade = velocidade + incremento;
    }

    /**
     * Freia o carro, em qualquer marcha (inclusive em ponto morto). Se o
     * decremento for maior que a velocidade atual, o carro para
     * (velocidade 0)
     *
     * @param decremento quanto será subtraído da velocidade, em km/h
     * @throws IllegalArgumentException se o decremento for negativo
     */
    public void frear(int decremento) {
        if (decremento < 0) {
            throw new IllegalArgumentException("Decremento negativo: " + decremento
                    + " (para aumentar a velocidade, use aumentarVelocidade)");
        }
        velocidade = Math.max(0, velocidade - decremento);
    }

    /**
     * @return a velocidade atual, em km/h
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * @return a marcha atual (-1 é ré e 0 é ponto morto)
     */
    public int getMarcha() {
        return marcha;
    }

    /**
     * @return o estado do carro em texto, por exemplo
     * "Carro a 120 km/h em 5ª marcha"
     */
    @Override
    public String toString() {
        if (marcha == RE) {
            return "Carro a " + velocidade + " km/h em marcha a ré";
        }
        if (marcha == PONTO_MORTO) {
            return "Carro a " + velocidade + " km/h em ponto morto";
        }
        return "Carro a " + velocidade + " km/h em " + marcha + "ª marcha";
    }
}
