package br.com.cidandrade.aulas;

import br.com.cidandrade.util.Mensagem;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatasApp {

    public static void main(String[] args) {
        Instant instInicial = Instant.now();
        Instant instFinal;
        Duration duracao;
        DateTimeFormatter dtf
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nascimento = LocalDate.of(1926,
                Month.APRIL, 21);
        LocalDate agora = LocalDate.now();
        Period periodo = Period.between(
                nascimento, agora);

        Mensagem.mensagem(agora);
        Mensagem.mensagem(agora.format(dtf));
        Mensagem.mensagem("Idade: " + periodo.getYears() + " anos");
        instFinal = Instant.now();
        duracao = Duration.between(
                instInicial, instFinal);
        Mensagem.mensagem("Este programa levou "
                + duracao.toMillis() + " milissegundos");
        System.exit(0);
    }

}
