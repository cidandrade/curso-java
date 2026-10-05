# 049 — Vida

**Tema:** Padrão Memento (comportamental)

## Objetivo

Salvar estados de um objeto e restaurar um deles depois, sem expor seus detalhes internos, como numa máquina do tempo.

## Conceitos-chave

- Memento: instantâneo imutável do estado
- Papéis: originador, memento e zelador (*caretaker*)
- Memento como classe aninhada estática do originador
- Histórico guardado numa `List`

## Classes principais

| Classe | Papel |
|---|---|
| `Vida` | **Originador**: cria mementos (`salvarMemento`) e restaura a partir deles |
| `Vida.Memento` | **Memento**: guarda o tempo |
| `VidaLongaApp` | **Zelador**: mantém a lista de mementos e restaura o primeiro |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/049-vida exec:java
```

Classe principal: `br.com.cidandrade.aulas.VidaLongaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [048 — Chat](../../07-padroes-comportamentais/048-chat) · [Sumário do curso](../../README.md#sumário) · [050 — Vida de Gato](../../07-padroes-comportamentais/050-vida-de-gato) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-049---Vida`](https://github.com/cidandrade/Codigo-049---Vida), hoje arquivado.
