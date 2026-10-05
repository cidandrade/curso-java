# 030 — Jogo de Cartas

**Tema:** Padrão Singleton (criacional)

## Objetivo

Garantir que exista um único baralho na aplicação, mesmo quando o código pede "outro" baralho.

## Conceitos-chave

- Singleton: construtor privado + método estático de acesso (`obterBaralho`)
- Instância criada sob demanda (*lazy initialization*)
- Efeito prático: as cartas retiradas do "segundo" baralho saem do mesmo objeto
- Reaproveita o baralho com `HashSet` da aula 018

## Classes principais

| Classe | Papel |
|---|---|
| `Baralho` | **Singleton**: `private Baralho()` e `static obterBaralho()` |
| `ConjuntoDeCartas`, `CartasManip` | Base e interface das coleções de cartas |
| `Mao`, `Carta`, `Face`, `Naipe` | Como na aula 018 |
| `JogoApp` | **Cliente**: pede o baralho duas vezes e mostra que é o mesmo |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 05-padroes-criacionais/030-jogo-de-cartas exec:java
```

Classe principal: `br.com.cidandrade.aulas.JogoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [029 — Nova Hamburgueria](../../05-padroes-criacionais/029-nova-hamburgueria) · [Sumário do curso](../../README.md#sumário) · [031 — Quem não tem cão](../../06-padroes-estruturais/031-quem-nao-tem-cao) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-030---Jogo-de-Cartas`](https://github.com/cidandrade/Codigo-030---Jogo-de-Cartas), hoje arquivado.
