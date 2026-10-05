# E002 — Temperatura

**Tema:** Exercício: classes, getters e setters

## Objetivo

Converter uma temperatura entre oito escalas: Kelvin, Celsius, Fahrenheit, Rankine, Rømer, Newton, Delisle e Réaumur.

## Conceitos-chave

- Um único atributo interno (Kelvin) e getters/setters que fazem a conversão
- Encapsulamento: quem usa a classe não sabe em que escala o valor está guardado
- Caracteres Unicode em `String` (`\u00D8`)

## Classes principais

| Classe | Papel |
|---|---|
| `Temperatura` | Guarda a temperatura em Kelvin e converte para as demais escalas |
| `TemperaturaApp` | Aplicação: mostra 184 K em todas as escalas |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl exercicios/E002-temperatura exec:java
```

Classe principal: `br.com.cidandrade.extras.TemperaturaApp`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [E001 — Números primos](../../exercicios/E001-numeros-primos) · [Sumário do curso](../../README.md#sumário) · [E003 — Triângulo](../../exercicios/E003-triangulo) →

## Origem

Este código veio do repositório [`cidandrade/E002---Temperatura`](https://github.com/cidandrade/E002---Temperatura), hoje arquivado.
