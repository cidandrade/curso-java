# 047 — Menus Diferentes

**Tema:** Padrão Iterator (comportamental)

## Objetivo

Percorrer da mesma forma dois cardápios guardados de jeitos diferentes, um em `List` e outro em vetor.

## Conceitos-chave

- Iterator: acesso sequencial sem expor a estrutura interna
- Uma interface de iteração própria (`hasNext`/`next`)
- Uma implementação delega ao `Iterator` da `List`; a outra controla a posição no vetor

## Classes principais

| Classe | Papel |
|---|---|
| `IteratorIface` | **Iterator** (interface) |
| `HotHouse` | Cardápio em `List`: **iterador concreto** que usa o `Iterator` da coleção |
| `Redondas` | Cardápio em vetor: **iterador concreto** com controle manual da posição |
| `ItemDeMenu` | Item do cardápio |
| `ExibeMenu` | **Cliente**: percorre os dois cardápios do mesmo jeito |
| `MenuApp` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/047-menus-diferentes exec:java
```

Classe principal: `br.com.cidandrade.aulas.MenuApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [046 — Comandos em Arquivo](../../07-padroes-comportamentais/046-comandos-em-arquivo) · [Sumário do curso](../../README.md#sumário) · [048 — Chat](../../07-padroes-comportamentais/048-chat) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-047---Menus-Diferentes`](https://github.com/cidandrade/Codigo-047---Menus-Diferentes), hoje arquivado.
