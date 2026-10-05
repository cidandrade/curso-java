# 053 — Chá ou Café

**Tema:** Padrão Template Method (comportamental)

## Objetivo

Definir numa superclasse o roteiro fixo de preparo de uma bebida e deixar as subclasses preencherem só os passos que mudam.

## Conceitos-chave

- Template Method: o algoritmo fica na superclasse; os passos variáveis, nas subclasses
- Passos com implementação padrão
- Gancho (*hook*): `clienteDeseja()` controla um passo opcional

## Classes principais

| Classe | Papel |
|---|---|
| `Bebida` | **Classe abstrata**: `prepararBebida()` é o método-modelo |
| `Cafe`, `Cha` | **Classes concretas**: `preparo()` e `adicionarIngrediente()` |
| `Cafeteria` | **Cliente**: prepara um café e um chá |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/053-cha-ou-cafe exec:java
```

Classe principal: `br.com.cidandrade.aulas.Cafeteria`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [052 — Descontos](../../07-padroes-comportamentais/052-descontos) · [Sumário do curso](../../README.md#sumário) · [054 — Carrinho de Compras](../../07-padroes-comportamentais/054-carrinho-de-compras) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-053---Ch--ou-Caf-`](https://github.com/cidandrade/Codigo-053---Ch--ou-Caf-), hoje arquivado.
