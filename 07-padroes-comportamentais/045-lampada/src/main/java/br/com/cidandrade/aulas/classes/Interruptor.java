package br.com.cidandrade.aulas.classes;

// Invoker
import br.com.cidandrade.aulas.ifaces.Comando;
import lombok.Setter;

@Setter
public class Interruptor {

    private Comando comando;

    public void aciona() {
        comando.executar();
    }
}
