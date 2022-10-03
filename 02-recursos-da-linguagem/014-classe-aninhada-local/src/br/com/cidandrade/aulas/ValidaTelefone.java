package br.com.cidandrade.aulas;

public class ValidaTelefone {

    static String expressao = "[^0-9]";

    public static void validarTelefone(String telefone) {
        final int COMPRIMENTO = 9;

        // Classe Local
        class NumeroDeTelefone {

            String telefoneFormatado = null;

            public NumeroDeTelefone(String numeroDeTelefone) {
                String numeroCorrente
                        = numeroDeTelefone.replaceAll(
                                expressao, "");
                telefoneFormatado = (numeroCorrente.length() == COMPRIMENTO
                        ? numeroCorrente : null);
            }

            public String getTelefoneFormatado() {
                return telefoneFormatado;
            }
        }

        NumeroDeTelefone numero
                = new NumeroDeTelefone(telefone);
        if (numero.getTelefoneFormatado() == null) {
            System.out.println("Telefone inválido");
        } else {
            System.out.println("Telefone é " + numero.getTelefoneFormatado());
        }
    }
}
