# 033 — Questionário

**Tema:** Padrão Bridge (estrutural)

## Objetivo

Aplicar de novo o Bridge, agora separando o gerenciamento e a apresentação de um questionário da forma como as questões são armazenadas.

## Conceitos-chave

- Bridge com abstração que tem operações próprias (`exibirTodas`)
- Abstração refinada que muda só a apresentação (`FormatadorDeQuestoes`)
- Implementação baseada em `List<String>`, trocável sem mexer na abstração

## Classes principais

| Classe | Papel |
|---|---|
| `GerenciadorDeQuestoes` | **Abstração**: navega, inclui, apaga e exibe questões delegando para `Questao` |
| `FormatadorDeQuestoes` | **Abstração refinada**: emoldura a listagem |
| `Questao` | **Implementador**: interface de armazenamento |
| `Questoes` | **Implementador concreto**: lista com dez perguntas |
| `QuestApp` | **Cliente**: exibe o questionário "Show dos Dez Reais" |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/033-questionario exec:java
```

Classe principal: `br.com.cidandrade.aulas.QuestApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [032 — Formas Coloridas](../../06-padroes-estruturais/032-formas-coloridas) · [Sumário do curso](../../README.md#sumário) · [034 — Empacotamento](../../06-padroes-estruturais/034-empacotamento) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-033---Question-rio`](https://github.com/cidandrade/Codigo-033---Question-rio), hoje arquivado.
