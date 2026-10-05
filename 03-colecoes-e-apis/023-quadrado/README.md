# 023 — Quadrado

**Tema:** Generics

## Objetivo

Criar uma classe genérica com tipo limitado a números.

## Conceitos-chave

- Classe genérica `Quadrado<T>`
- Tipo limitado: `<T extends Number>`
- Autoboxing de `int` para `Integer`
- `doubleValue()`

## Classes principais

| Classe | Papel |
|---|---|
| `Quadrado` | Classe genérica que eleva um número ao quadrado |
| `QuadradoApp` | Aplicação: lê um inteiro e mostra o quadrado |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/023-quadrado exec:java
```

Classe principal: `br.com.cidandrade.aulas.QuadradoApp`. Na IDE, basta executar essa classe.

Entrada e saída por caixas de diálogo (`JOptionPane`); é preciso ambiente gráfico.

## Navegação

← [022 — Lambda](../../03-colecoes-e-apis/022-lambda) · [Sumário do curso](../../README.md#sumário) · [024 — Calculadora](../../04-interface-grafica-e-integracao/024-calculadora) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-023---Quadrado`](https://github.com/cidandrade/Codigo-023---Quadrado), hoje arquivado.
