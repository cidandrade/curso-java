# 011 — String

**Tema:** A classe String

## Objetivo

Explorar os principais métodos da classe `String`, a concatenação com números e o `StringBuilder`.

## Conceitos-chave

- `charAt`, `compareTo`, `contains`, `equals`, `replace`, `substring`
- `toUpperCase`, `toLowerCase`, `length`, `lastIndexOf`, `split`
- Concatenação: `1024 + "4"` × `"4" + 1024` × `1024 + Integer.parseInt("4")`
- `String.format`
- `StringBuilder` e `append`

## Classes principais

| Classe | Papel |
|---|---|
| `StringsApp` | Aplicação com todos os exemplos |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/011-string exec:java
```

Classe principal: `br.com.cidandrade.aulas.StringsApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [010 — POJO com Lombok](../../02-recursos-da-linguagem/010-pojo-com-lombok) · [Sumário do curso](../../README.md#sumário) · [012 — Decisão e repetição](../../02-recursos-da-linguagem/012-decisao-e-repeticao) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-011---String`](https://github.com/cidandrade/Codigo-011---String), hoje arquivado.
