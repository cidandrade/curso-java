package br.com.cidandrade.aulas;

import java.math.BigDecimal;

/**
 * Classe para demonstração de uso de variáveis e tipos de dados
 *
 * @author cidandrade
 */
public class SerHumano {

    // Atributos não estáticos
    byte idade;
    short qtdeOssos;
    int qtdeRefeicoes;
    long qtdeCelulas;
    float peso;
    double fortuna;
    boolean portadorNecessidadesEspeciais;
    char inicial;
    String nome;
    BigDecimal heranca;

    // Atributos estáticos
    double populacao = 0;

    void inicializar() {
        idade = 0;
        qtdeOssos = 206;
        qtdeRefeicoes = 0;
        qtdeCelulas = 37_000_000_000_000L;
        peso = 0.0F;
        fortuna = 0.0;
        portadorNecessidadesEspeciais = false;
        inicial = '\u0041';
        nome = "Asdrúbal";
        populacao = 7.9e9;
        heranca = new BigDecimal("1234567");
    }

    void alteraIdade() {
        idade = 26;
        idade = 0x1A;
        idade = 0b11010;
    }

    void nascer() {
        populacao = populacao + 1;
    }

    void morrer() {
        populacao = populacao - 1;
    }

    // ganho é um parâmetro
    void engordar(float ganho) {
        // novoPeso é uma variável local
        float novoPeso = peso + ganho;
        peso = novoPeso;
    }

    void emagrecer(float perda) {
        peso = peso - perda;
    }
}
