# 021 — Datas

**Tema:** API java.time

## Objetivo

Trabalhar com datas, períodos e medição de tempo usando a API `java.time`.

## Conceitos-chave

- `LocalDate.of` e `LocalDate.now`
- `DateTimeFormatter.ofPattern("dd/MM/yyyy")`
- `Period.between` para calcular idade
- `Instant` e `Duration` para medir tempo de execução

## Classes principais

| Classe | Papel |
|---|---|
| `DatasApp` | Aplicação com todos os exemplos |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/021-datas exec:java
```

Classe principal: `br.com.cidandrade.aulas.DatasApp`. Na IDE, basta executar essa classe.

Parte da saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [020 — Menu com Map](../../03-colecoes-e-apis/020-menu-com-map) · [Sumário do curso](../../README.md#sumário) · [022 — Lambda](../../03-colecoes-e-apis/022-lambda) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-021---Datas`](https://github.com/cidandrade/Codigo-021---Datas), hoje arquivado.
