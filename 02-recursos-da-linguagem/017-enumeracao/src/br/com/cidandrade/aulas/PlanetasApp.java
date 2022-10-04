package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

/**
 *
 * @author cidandrade
 */
public class PlanetasApp {

    public static void main(String[] args) {
        double pesoNaTerra = 80.0;
        for (Planeta p : Planeta.values()) {
            Mensagem.mensagem(String.format(
                    "Uma pessoa pesa %.1f Kg em %s",
                    p.equivalente(pesoNaTerra),
                    p.getNome()));
        }
        System.exit(0);
    }

}
