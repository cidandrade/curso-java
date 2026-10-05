# 015 — Classe aninhada anônima

**Tema:** Classe anônima

## Objetivo

Comparar uma classe local com nome com uma classe anônima que implementa a mesma interface.

## Conceitos-chave

- Interface aninhada
- Classe local que implementa uma interface (`CumprimentoEmIngles`)
- Classe anônima: `new OlaMundo() { ... }`
- Quando usar cada uma

## Classes principais

| Classe | Papel |
|---|---|
| `OlaMundoAnonimo` | Declara a interface `OlaMundo`, a classe local e a classe anônima |
| `OlaMundoAnonimoApp` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/015-classe-aninhada-anonima exec:java
```

Classe principal: `br.com.cidandrade.aulas.OlaMundoAnonimoApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [014 — Classe aninhada local](../../02-recursos-da-linguagem/014-classe-aninhada-local) · [Sumário do curso](../../README.md#sumário) · [016 — Matemática](../../02-recursos-da-linguagem/016-matematica) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-015---Classe-aninhada-an-nima`](https://github.com/cidandrade/Codigo-015---Classe-aninhada-an-nima), hoje arquivado.
