# 034 — Empacotamento

**Tema:** Padrão Composite (estrutural)

## Objetivo

Calcular o preço de um pedido formado por caixas que contêm produtos e outras caixas, tratando produto e caixa da mesma maneira.

## Conceitos-chave

- Composite: estrutura em árvore de partes e todos
- Interface comum para folhas e compostos (`Preco`)
- Recursão: a caixa soma o preço de tudo o que contém

## Classes principais

| Classe | Papel |
|---|---|
| `Preco` | **Componente**: `obtemPreco()` |
| `Produto` | **Folha**: devolve o próprio preço |
| `Caixa` | **Composto**: guarda `Preco`s e soma seus preços |
| `PacotesApp` | **Cliente**: monta caixas aninhadas e mostra o total |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/034-empacotamento exec:java
```

Classe principal: `br.com.cidandrade.aulas.PacotesApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [033 — Questionário](../../06-padroes-estruturais/033-questionario) · [Sumário do curso](../../README.md#sumário) · [035 — Cafeteria](../../06-padroes-estruturais/035-cafeteria) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-034---Empacotamento`](https://github.com/cidandrade/Codigo-034---Empacotamento), hoje arquivado.
