# 018 — Baralho com HashSet

**Tema:** Coleções: Set e HashSet

## Objetivo

Modelar um baralho com `Set` e retirar cartas para formar uma mão, usando enums para face e naipe.

## Conceitos-chave

- Interface `Set` e implementação `HashSet`
- `Iterator`
- `removeAll`, `contains`, `size`, `isEmpty`, `clear`
- Enums como tipos de domínio (`Face`, `Naipe`)
- Classe abstrata que implementa interface
- Referência a método (`System.out::println`) e lambda em `forEach`
- `Carta` não sobrescreve `equals`/`hashCode`, então `contemCarta(face, naipe)` não encontra a carta; a aula 019 resolve isso

## Classes principais

| Classe | Papel |
|---|---|
| `CartasManip` | Interface com as operações sobre um conjunto de cartas |
| `ConjuntoDeCartas` | Classe abstrata: implementa as operações sobre um `Set<Carta>` |
| `Baralho` | Cria as 52 cartas e retira uma mão |
| `Mao` | Cartas retiradas; mostra a mão numa caixa de diálogo |
| `Carta` | Face + naipe |
| `Face`, `Naipe` | Enums das faces e dos naipes |
| `BaralhoApp` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/018-baralho-com-hashset exec:java
```

Classe principal: `br.com.cidandrade.aulas.BaralhoApp`. Na IDE, basta executar essa classe.

Parte da saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [017 — Enumeração](../../02-recursos-da-linguagem/017-enumeracao) · [Sumário do curso](../../README.md#sumário) · [019 — Baralho com List](../../03-colecoes-e-apis/019-baralho-com-list) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-018---Baralho-com-HashSet`](https://github.com/cidandrade/Codigo-018---Baralho-com-HashSet), hoje arquivado.
