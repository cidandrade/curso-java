package br.com.cidandrade.aulas.extras;

import br.com.cidandrade.util.Entrada;
import br.com.cidandrade.util.Mensagem;

public class AchaPrimo {

    public static void main(String[] args) {
        long testeDePrimo = 0L;
        boolean ePrimo = true;

        try {
            testeDePrimo = Entrada.getLong("Entre com um número: ");
        } catch (NumberFormatException e) {
            Mensagem.msgErro("Erro na entrada de dados");
            System.exit(1);
        }
        if (testeDePrimo > 1) {
            // Para 2 e 3 já aceita que são primos
            if (testeDePrimo > 3) {
                // Verifica se o número somado ou subtraído de 1 
                // são múltiplos de 6, um indicador que pode ser primo
                ePrimo = (((testeDePrimo + 1) % 6 == 0)
                        || ((testeDePrimo - 1) % 6 == 0));
                // Testa para números entre 2 e a metade do número
                for (long i = 2; i <= (long) ((testeDePrimo) / 2)
                        && ePrimo; i++) {
                    ePrimo = ((testeDePrimo % i) != 0);
                }
            }
            Mensagem.mensagem((ePrimo ? "Primo!!!" : "Não é primo!!!"));
        } else {
            // 1 ou menos
            Mensagem.msgErro("Não válido");
        }
        System.exit(0);
    }
}
