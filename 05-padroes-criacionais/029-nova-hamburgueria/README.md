# 029 — Nova Hamburgueria

**Tema:** Padrão Prototype (criacional)

## Objetivo

Acrescentar à hamburgueria da aula 028 a criação de um hambúrguer a partir de outro já existente, com `clone()`.

## Conceitos-chave

- Prototype: criar objetos copiando um protótipo
- Interface `Cloneable` e sobrescrita de `clone()`
- Builder (aula 028) e Prototype juntos
- Discussão: o `clone()` desta aula devolve o próprio objeto (`return this`). Como o hambúrguer é imutável, o efeito no pedido é o mesmo, mas vale comparar com uma cópia de verdade (`super.clone()`)

## Classes principais

| Classe | Papel |
|---|---|
| `Hamburguer` | **Protótipo**: implementa `Cloneable` |
| `Hamburguer.Construtor` | Builder da aula 028 |
| `Pedido` | Agrupa os hambúrgueres |
| `ParaisoApp` | **Cliente**: cria `h3` clonando `h2` |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 05-padroes-criacionais/029-nova-hamburgueria exec:java
```

Classe principal: `br.com.cidandrade.aulas.ParaisoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [028 — Hamburgueria](../../05-padroes-criacionais/028-hamburgueria) · [Sumário do curso](../../README.md#sumário) · [030 — Jogo de Cartas](../../05-padroes-criacionais/030-jogo-de-cartas) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-029---Nova-Hamburgueria`](https://github.com/cidandrade/Codigo-029---Nova-Hamburgueria), hoje arquivado.
