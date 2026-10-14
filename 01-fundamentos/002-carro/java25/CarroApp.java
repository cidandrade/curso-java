// Aplicação de exemplo sobre classes, na forma do Java 25: cria dois carros
// e mostra que cada objeto guarda o seu próprio estado.
//
// Este é um arquivo compacto: não declara pacote nem a classe da aplicação.
// O compilador cria uma classe implícita a partir do arquivo, e a classe
// Carro, declarada aqui dentro, vira uma classe aninhada dela. Por isso,
// nenhum outro arquivo consegue usar este Carro: quando o programa cresce,
// Carro volta a ter arquivo e pacote próprios, como em src/main/java.
//
// Requer o JDK 25 ou mais recente. Para executar, na raiz do repositório:
//     java 01-fundamentos/002-carro/java25/CarroApp.java
//
// Este arquivo fica fora de src/main/java, e por isso o Maven não o compila:
// o curso compila em Java 17, em que esta forma não existe.
//
// Autor: profandrade@gmail.com

void main() {
    Carro carro1 = new Carro();
    carro1.mudarMarcha(1);
    carro1.aumentarVelocidade(10);
    carro1.mudarMarcha(2);
    carro1.aumentarVelocidade(20);
    carro1.mudarMarcha(3);
    carro1.aumentarVelocidade(30);
    carro1.mudarMarcha(4);
    carro1.aumentarVelocidade(30);
    carro1.frear(10);
    carro1.mudarMarcha(5);
    carro1.aumentarVelocidade(40);
    IO.println(carro1);
    // Experimente: descomente a linha abaixo. O carro está a 120 km/h, então
    // o objeto recusa a ré e o programa para com uma IllegalStateException
    // carro1.mudarMarcha(-1);

    Carro carro2 = new Carro();
    carro2.mudarMarcha(1);
    carro2.aumentarVelocidade(10);
    carro2.mudarMarcha(2);
    carro2.aumentarVelocidade(15);
    carro2.mudarMarcha(3);
    carro2.aumentarVelocidade(20);
    carro2.mudarMarcha(4);
    carro2.aumentarVelocidade(20);
    carro2.frear(15);
    carro2.mudarMarcha(5);
    carro2.aumentarVelocidade(30);
    IO.println(carro2);

    // Para engatar a ré, o carro precisa estar parado
    carro2.frear(80);
    carro2.mudarMarcha(-1);
    carro2.aumentarVelocidade(5);
    IO.println(carro2);
}

// Um carro que garante o próprio estado válido: marcha de -1 (ré) a 5,
// com 0 como ponto morto; velocidade nunca negativa; sem acelerar em ponto
// morto; sem entrar na ré nem sair dela com o carro andando.
class Carro {

    private static final int RE = -1;
    private static final int PONTO_MORTO = 0;
    private static final int MARCHA_MAXIMA = 5;

    private int velocidade = 0;
    private int marcha = PONTO_MORTO;

    void mudarMarcha(int novaMarcha) {
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

    void aumentarVelocidade(int incremento) {
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

    void frear(int decremento) {
        if (decremento < 0) {
            throw new IllegalArgumentException("Decremento negativo: " + decremento
                    + " (para aumentar a velocidade, use aumentarVelocidade)");
        }
        velocidade = Math.max(0, velocidade - decremento);
    }

    int getVelocidade() {
        return velocidade;
    }

    int getMarcha() {
        return marcha;
    }

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
