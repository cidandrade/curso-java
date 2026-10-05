# 037 — Novo Formas Coloridas

**Tema:** Padrão Facade (estrutural), sobre o Bridge da aula 032

## Objetivo

Esconder atrás de uma fachada a montagem das formas coloridas da aula 032: o cliente só escolhe forma e cor por enums.

## Conceitos-chave

- Facade: interface simples para um subsistema
- O cliente deixa de conhecer as classes concretas (`Quadrado`, `Azul`...)
- Enums como parâmetros da fachada
- Facade combinado com Bridge

## Classes principais

| Classe | Papel |
|---|---|
| `FormaFacade` | **Fachada**: `getForma(FormaEnum, CorEnum)` monta a forma com a cor |
| `FormaEnum`, `CorEnum` | Opções disponíveis para o cliente |
| `Forma`, `Quadrado`, `Hexagono`, `Cor_Ponte`, `Azul`, `Verde` | **Subsistema**: o Bridge da aula 032 |
| `FormaApp` | **Cliente** |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/037-novo-formas-coloridas exec:java
```

Classe principal: `br.com.cidandrade.aulas.FormaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [036 — Árvore de Natal](../../06-padroes-estruturais/036-arvore-de-natal) · [Sumário do curso](../../README.md#sumário) · [038 — Novo Carro](../../06-padroes-estruturais/038-novo-carro) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-037---Novo-Formas-Coloridas`](https://github.com/cidandrade/Codigo-037---Novo-Formas-Coloridas), hoje arquivado.
