package br.com.cidandrade.aulas.fidelidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Com as dependências injetadas, o teste escolhe o dia da semana (relógio
 * fixo) e lê os avisos (notificador falso). Nada disso é possível com a
 * FidelidadeAcoplada
 *
 * @author profandrade@gmail.com
 * @version 1.0.0
 */
@DisplayName("Programa de fidelidade")
class ProgramaFidelidadeTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDate QUARTA = LocalDate.of(2026, 10, 14);
    private static final LocalDate QUINTA = LocalDate.of(2026, 10, 15);

    private final RepositorioClientes repositorio = new RepositorioEmMemoria();
    private final List<String> avisos = new ArrayList<>();
    private final Notificador notificadorFalso = (cliente, msg) -> avisos.add(cliente.getNome() + ": " + msg);

    private ProgramaFidelidade programaEm(LocalDate dia) {
        Clock relogioFixo = Clock.fixed(dia.atStartOfDay(FUSO).toInstant(), FUSO);
        ProgramaFidelidade programa = new ProgramaFidelidade(repositorio, notificadorFalso, relogioFixo);
        programa.cadastrar("123", "Ana");
        return programa;
    }

    @Test
    @DisplayName("1 ponto por real inteiro; os centavos não contam")
    void umPontoPorReal() {
        ProgramaFidelidade programa = programaEm(QUINTA);

        assertEquals(23, programa.registrarCompra("123", new BigDecimal("23.90")));
    }

    @Test
    @DisplayName("às quartas-feiras os pontos valem o dobro")
    void quartaEmDobro() {
        ProgramaFidelidade programa = programaEm(QUARTA);

        assertEquals(46, programa.registrarCompra("123", new BigDecimal("23.90")));
    }

    @Test
    @DisplayName("os pontos ficam guardados no repositório")
    void pontosGuardados() {
        ProgramaFidelidade programa = programaEm(QUINTA);
        programa.registrarCompra("123", new BigDecimal("30.00"));
        programa.registrarCompra("123", new BigDecimal("12.00"));

        assertEquals(42, repositorio.buscar("123").orElseThrow().getPontos());
    }

    @Test
    @DisplayName("avisa o cupom uma única vez, ao chegar a 100 pontos")
    void avisoDoCupom() {
        ProgramaFidelidade programa = programaEm(QUINTA);

        programa.registrarCompra("123", new BigDecimal("60.00"));
        assertTrue(avisos.isEmpty());

        programa.registrarCompra("123", new BigDecimal("45.00"));
        programa.registrarCompra("123", new BigDecimal("10.00"));

        assertEquals(List.of("Ana: Você chegou a 105 pontos e ganhou um cupom!"), avisos);
    }

    @Test
    @DisplayName("recusa compra de cliente não cadastrado")
    void clienteDesconhecido() {
        ProgramaFidelidade programa = programaEm(QUINTA);

        assertThrows(IllegalArgumentException.class,
                () -> programa.registrarCompra("999", new BigDecimal("10.00")));
    }
}
