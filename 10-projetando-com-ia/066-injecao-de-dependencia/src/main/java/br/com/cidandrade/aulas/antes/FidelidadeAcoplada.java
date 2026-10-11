package br.com.cidandrade.aulas.antes;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * ANTES: as mesmas regras do pacote fidelidade, com as dependências
 * escondidas lá dentro. Funciona, mas não dá para testar de forma
 * confiável nem trocar nenhuma peça
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
public class FidelidadeAcoplada {

    // Dependência 1: o armazenamento é criado aqui. Para guardar num banco,
    // é preciso reescrever a classe
    private final Map<String, Integer> pontos = new HashMap<>();

    public void registrarCompra(String cpf, BigDecimal valor) {
        int ganhos = valor.intValue();
        // Dependência 2: o relógio do sistema. A regra da quarta-feira só
        // pode ser testada... numa quarta-feira
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            ganhos *= 2;
        }
        int antes = pontos.getOrDefault(cpf, 0);
        pontos.put(cpf, antes + ganhos);
        // Dependência 3: a saída vai direto para o console. Um teste não
        // consegue saber se o aviso foi dado
        if (antes < 100 && antes + ganhos >= 100) {
            System.out.println("Parabéns, " + cpf + "! Você ganhou um cupom.");
        }
    }

    public int pontosDe(String cpf) {
        return pontos.getOrDefault(cpf, 0);
    }
}
