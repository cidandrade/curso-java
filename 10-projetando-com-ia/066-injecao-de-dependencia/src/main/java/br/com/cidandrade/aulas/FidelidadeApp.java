package br.com.cidandrade.aulas;

import br.com.cidandrade.aulas.fidelidade.NotificadorConsole;
import br.com.cidandrade.aulas.fidelidade.ProgramaFidelidade;
import br.com.cidandrade.aulas.fidelidade.RepositorioEmMemoria;
import java.math.BigDecimal;
import java.time.Clock;

/**
 * Composition root: o único lugar que conhece as classes concretas e
 * monta o objeto com elas. Para usar um banco de dados ou e-mail, só
 * esta classe muda
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class FidelidadeApp {

    public static void main(String[] args) {
        ProgramaFidelidade programa = new ProgramaFidelidade(
                new RepositorioEmMemoria(),
                new NotificadorConsole(),
                Clock.systemDefaultZone());

        programa.cadastrar("123", "Ana");
        System.out.println("Compra de R$ 60,00: +"
                + programa.registrarCompra("123", new BigDecimal("60.00")) + " pontos");
        System.out.println("Compra de R$ 45,90: +"
                + programa.registrarCompra("123", new BigDecimal("45.90")) + " pontos");
    }
}
