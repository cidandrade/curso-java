package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class ValidaTelefone {

    static final String EXPRESSAO = "[^0-9]";

    public static void validarTelefone(String telefone) {
        final int COMPRIMENTO = 9;

        // Classe Local
        class NumeroDeTelefone {

            String telefoneFormatado = null;

            public NumeroDeTelefone(String numeroDeTelefone) {
                String numeroCorrente
                        = numeroDeTelefone.replaceAll(
                                EXPRESSAO, "");
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
            Mensagem.msgErro("Telefone inválido");
        } else {
            Mensagem.mensagem("Telefone é " + numero.getTelefoneFormatado());
        }
    }
}
