# 016 — Matemática

**Tema:** A classe Math e precisão numérica

## Objetivo

Usar os métodos da classe `Math` e mostrar por que `BigDecimal` é necessário em cálculos exatos.

## Conceitos-chave

- `Math.PI`, `abs`, `ceil`, `floor`, `log10`, `max`, `min`, `pow`, `sqrt`, `random`
- Número pseudoaleatório numa faixa
- Erro de arredondamento: `0.1 + 0.2` em `double` × em `BigDecimal`
- Varargs (`double...`) e sobrecarga

## Classes principais

| Classe | Papel |
|---|---|
| `Matematica` | Aplicação com todos os exemplos |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/016-matematica exec:java
```

Classe principal: `br.com.cidandrade.aulas.Matematica`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [015 — Classe aninhada anônima](../../02-recursos-da-linguagem/015-classe-aninhada-anonima) · [Sumário do curso](../../README.md#sumário) · [017 — Enumeração](../../02-recursos-da-linguagem/017-enumeracao) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-016---Matematica`](https://github.com/cidandrade/Codigo-016---Matematica), hoje arquivado.
