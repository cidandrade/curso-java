# 013 — Aninhamento de classes

**Tema:** Classes aninhadas: interna e estática

## Objetivo

Declarar classes dentro de outra classe e mostrar a diferença entre classe interna e classe aninhada estática.

## Conceitos-chave

- Classe interna (*inner class*): acessa os atributos do objeto externo (`Motor` lê `acelerando`)
- Classe aninhada estática: não depende de uma instância externa (`Pneu`)
- Composição: o carro tem motor e pneus
- Atributos `final` inicializados no construtor

## Classes principais

| Classe | Papel |
|---|---|
| `Carro` | Classe externa; contém `Motor` (interna) e `Pneu` (aninhada estática) |
| `AninhamentoApp` | Aplicação: cria um carro e mostra sua descrição |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/013-aninhamento-de-classes exec:java
```

Classe principal: `br.com.cidandrade.aulas.AninhamentoApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [012 — Decisão e repetição](../../02-recursos-da-linguagem/012-decisao-e-repeticao) · [Sumário do curso](../../README.md#sumário) · [014 — Classe aninhada local](../../02-recursos-da-linguagem/014-classe-aninhada-local) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-013---Aninhamento-de-classes`](https://github.com/cidandrade/Codigo-013---Aninhamento-de-classes), hoje arquivado.
