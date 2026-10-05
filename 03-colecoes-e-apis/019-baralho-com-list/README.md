# 019 — Baralho com List

**Tema:** Coleções: List, ordenação e embaralhamento

## Objetivo

Refazer o baralho com `List`, que permite embaralhar, ordenar e acessar por posição.

## Conceitos-chave

- Interface `List` e implementação `ArrayList`
- `Collections.shuffle` e `Collections.sort`
- `Comparable` e `compareTo`
- Sobrescrita de `equals` e `hashCode`
- Enums com valor numérico
- Comparação com a versão `Set` da aula 018

## Classes principais

| Classe | Papel |
|---|---|
| `CartasManip` | Interface; ganha `cartaPorPosicao`, `ordena` e `embaralha` |
| `ConjuntoDeCartas` | Implementação sobre `List<Carta>` |
| `Baralho` | Embaralha antes de retirar a mão |
| `Mao` | Cartas retiradas |
| `Carta` | Implementa `Comparable<Carta>`, `equals` e `hashCode` |
| `Face`, `Naipe` | Enums com valor para ordenação |
| `BaralhoApp` | Aplicação: imprime o baralho ordenado no console e retira uma mão |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/019-baralho-com-list exec:java
```

Classe principal: `br.com.cidandrade.aulas.BaralhoApp`. Na IDE, basta executar essa classe.

Parte da saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [018 — Baralho com HashSet](../../03-colecoes-e-apis/018-baralho-com-hashset) · [Sumário do curso](../../README.md#sumário) · [020 — Menu com Map](../../03-colecoes-e-apis/020-menu-com-map) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-019---Baralho-com-List`](https://github.com/cidandrade/Codigo-019---Baralho-com-List), hoje arquivado.
