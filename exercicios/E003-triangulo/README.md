# E003 — Triângulo

**Tema:** Exercício: decisões encadeadas

## Objetivo

Dados três lados, dizer se formam um triângulo e classificá-lo em equilátero, isósceles ou escaleno.

## Conceitos-chave

- Condição de existência do triângulo (desigualdade triangular)
- `if`/`else if` encadeados
- Atributos `final` e método privado auxiliar

## Classes principais

| Classe | Papel |
|---|---|
| `Triangulo` | Valida os lados e classifica o triângulo |
| `TrianguloApp` | Aplicação: testa cinco casos |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl exercicios/E003-triangulo exec:java
```

Classe principal: `br.com.cidandrade.extras.TrianguloApp`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [E002 — Temperatura](../../exercicios/E002-temperatura) · [Sumário do curso](../../README.md#sumário)

## Origem

Este código veio do repositório [`cidandrade/Extra-003---Tri-ngulo`](https://github.com/cidandrade/Extra-003---Tri-ngulo), hoje arquivado.
