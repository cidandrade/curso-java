package br.com.cidandrade.aulas;

/**
 *
 * @author cidandrade
 */
public enum Planeta {
    MERCURIO("Mercúrio", 3.303e+23, 2.4397e6),
    VENUS("Vênus", 4.869e+24, 6.0518e6),
    TERRA("Terra", 5.976e+24, 6.37814e6),
    MARTE("Marte", 6.421e+23, 3.3972e6),
    JUPITER("Júpiter", 1.9e+27, 7.1492e7),
    SATURNO("Saturno", 5.688e+26, 6.0268e7),
    URANO("Urano", 8.686e+25, 2.5559e7),
    NETUNO("Netuno", 1.024e+26, 2.4746e7);

    private final double massa, raio;
    private final String nome;
    public static final double G = 6.67300E-11;

    private Planeta(String nome, double massa, double raio) {
        this.massa = massa;
        this.raio = raio;
        this.nome = nome;
    }

    double gravidadeNaSuperficie() {
        return G * massa / Math.pow(raio, 2);
    }

    double pesoNaSuperficie(double outraMassa) {
        return outraMassa * gravidadeNaSuperficie();
    }

    public double equivalente(double naTerra) {
        return pesoNaSuperficie(naTerra
                / TERRA.gravidadeNaSuperficie());
    }

    public String getNome() {
        return nome;
    }
}
