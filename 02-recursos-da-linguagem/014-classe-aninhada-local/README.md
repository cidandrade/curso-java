# 014 — Classe aninhada local

**Tema:** Classe local

## Objetivo

Declarar uma classe dentro de um método e usá-la só ali.

## Conceitos-chave

- Classe local declarada dentro de um método
- Acesso a variáveis locais `final` ou efetivamente finais (`COMPRIMENTO`)
- Expressão regular com `replaceAll("[^0-9]", "")`

## Classes principais

| Classe | Papel |
|---|---|
| `ValidaTelefone` | Método `validarTelefone`, que declara a classe local `NumeroDeTelefone` |
| `FoneApp` | Aplicação: valida um telefone válido e um inválido |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/014-classe-aninhada-local exec:java
```

Classe principal: `br.com.cidandrade.aulas.FoneApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [013 — Aninhamento de classes](../../02-recursos-da-linguagem/013-aninhamento-de-classes) · [Sumário do curso](../../README.md#sumário) · [015 — Classe aninhada anônima](../../02-recursos-da-linguagem/015-classe-aninhada-anonima) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-014---Classe-aninhada-local`](https://github.com/cidandrade/Codigo-014---Classe-aninhada-local), hoje arquivado.
