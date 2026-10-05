package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.ifaces.Cachorro;
import br.com.cidandrade.aulas.ifaces.Gato;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GatoAdaptador implements Cachorro {

    private Gato gato;

    @Override
    public void latir() {
        gato.miar();
    }

    @Override
    public void perseguir() {
        gato.perseguir();
    }

    @Override
    public void abanarRabo() {
        throw new UnsupportedOperationException(
                "Não, abanar o rabo já é demais");
    }

}
