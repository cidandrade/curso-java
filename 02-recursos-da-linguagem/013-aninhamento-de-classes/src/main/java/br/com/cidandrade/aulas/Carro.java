package br.com.cidandrade.aulas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carro {

    private final String modelo;
    private final String fabricante;
    private final Motor motor;
    private final Pneu[] pneu;
    private final boolean acelerando;

    @Override
    public String toString() {
        return "Carro " + fabricante + " " + modelo + ", "
                + motor + " com " + pneu.length + " " + pneu[0];
    }

    public Carro() {
        fabricante = "Serengeti";
        modelo = "Ocapi";
        motor = new Motor(1.3F);
        pneu = new Pneu[4];
        for (int i = 0; i < 4; i++) {
            pneu[i] = new Pneu((byte) 16);
        }
        acelerando = true;
    }

    // Classe interna
    private class Motor {

        private final float cilindradas;

        @Override
        public String toString() {
            String cilindradasString = String.format(
                    "%.1f", cilindradas)
                    .replace(',', '.');
            return String.format("Motor %s %s",
                    cilindradasString,
                    (acelerando ? "acelerando" : "marcha lenta"));
        }

        public Motor(float cilindradas) {
            this.cilindradas = cilindradas;
        }
    }

    // Classe aninhada estática
    private static class Pneu {

        private final byte aro;

        @Override
        public String toString() {
            return "pneus aro " + aro;
        }

        public Pneu(byte aro) {
            this.aro = aro;
        }
    }
}
