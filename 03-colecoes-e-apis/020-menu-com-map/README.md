# 020 — Menu com Map

**Tema:** Coleções: Map e HashMap

## Objetivo

Montar o cardápio de um restaurante com `Map`, usando o nome do prato como chave.

## Conceitos-chave

- Interface `Map` e implementação `HashMap`
- `put`, `get`, `containsKey`, `remove`, `size`
- `forEach` com lambda de dois parâmetros `(k, v)`
- Ordenação das chaves com `Collections.sort`

## Classes principais

| Classe | Papel |
|---|---|
| `Prato` | Nome, descrição e preço |
| `Menu` | Mapa nome → prato, com consultas e impressão ordenada |
| `MenuApp` | Aplicação: procura um prato e imprime o cardápio em ordem alfabética |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/020-menu-com-map exec:java
```

Classe principal: `br.com.cidandrade.aulas.MenuApp`. Na IDE, basta executar essa classe.

Parte da saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [019 — Baralho com List](../../03-colecoes-e-apis/019-baralho-com-list) · [Sumário do curso](../../README.md#sumário) · [021 — Datas](../../03-colecoes-e-apis/021-datas) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-020---Menu-com-Map`](https://github.com/cidandrade/Codigo-020---Menu-com-Map), hoje arquivado.
