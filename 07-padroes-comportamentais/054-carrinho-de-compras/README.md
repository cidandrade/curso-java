# 054 — Carrinho de Compras

**Tema:** Padrão Visitor (comportamental)

## Objetivo

Calcular o preço de itens diferentes de um carrinho (livros e frutas) com um visitante, sem colocar a regra de preço nas classes dos itens.

## Conceitos-chave

- Visitor: separar uma operação da estrutura de objetos
- *Double dispatch*: `item.aceitar(visitante)` → `visitante.visitar(this)`
- Sobrecarga de `visitar` por tipo de elemento
- Novas operações sem alterar os elementos

## Classes principais

| Classe | Papel |
|---|---|
| `Elemento` | **Elemento** (interface): `aceitar(visitante)` |
| `Livro`, `Fruta` | **Elementos concretos** |
| `VisitanteCarrinhoIface` | **Visitante** (interface) |
| `VisitanteCarrinho` | **Visitante concreto**: desconto em livros caros; frutas por peso |
| `CarrinhoApp` | **Cliente**: soma o carrinho |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/054-carrinho-de-compras exec:java
```

Classe principal: `br.com.cidandrade.aulas.CarrinhoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [053 — Chá ou Café](../../07-padroes-comportamentais/053-cha-ou-cafe) · [Sumário do curso](../../README.md#sumário) · [055 — MVC](../../08-arquitetura-e-persistencia/055-mvc) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-054---Carrinho-de-Compras`](https://github.com/cidandrade/Codigo-054---Carrinho-de-Compras), hoje arquivado.
