package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.classes.Casa;
import br.com.cidandrade.aulas.classes.Endereco;
import br.com.cidandrade.aulas.classes.Mago;
import br.com.cidandrade.aulas.classes.ManipulaObjeto;
import br.com.cidandrade.util.Data;
import br.com.cidandrade.util.Mensagem;

/**
 *
 * @author cidandrade
 */
public class SalvaApp {

    public static void main(String[] args) {
        Endereco privetDrive = new Endereco("Drive",
                "Privet", "4",
                "Under the Stairs", "North Watford",
                "Little Whinging", "Surrey", "Inglaterra");
        Mago harry = new Mago("Harry", "James", "Potter",
                privetDrive,
                Data.getLocalDate(31, 7, 1980),
                Casa.GRYFFINDOR, "Teste de serialização");
        Mensagem.mensagem("Antes da serialização: \n" + harry);
        System.out.println(harry);
        ManipulaObjeto.salva("harry_file", harry);
        Mago mago = ManipulaObjeto.recupera("harry_file");
        Mensagem.mensagem("Após a serialização: \n" + mago);
        System.exit(0);
    }

}
