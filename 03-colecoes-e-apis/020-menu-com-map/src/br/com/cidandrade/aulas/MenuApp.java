package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;

public class MenuApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu trono = new Menu();
        String prato = "Doce Sansa";
        trono.popular();
        if (trono.temPrato(prato)) {
            Mensagem.mensagem("Hoje tem " + prato);
        } else {
            Mensagem.msgErro("Não tem " + prato);
        }
        trono.imprimeOrdenado();
        System.exit(0);
    }

}
