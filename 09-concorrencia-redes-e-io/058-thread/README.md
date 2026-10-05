# 058 — Thread

**Tema:** Threads

## Objetivo

Criar e iniciar três threads que disputam o mesmo contador e observar que a ordem de execução não é previsível.

## Conceitos-chave

- `Thread` e `Runnable`
- Lambda como `Runnable`
- `start()` × execução sequencial
- `Thread.yield()`
- Condição de corrida: `++i` num contador compartilhado sem sincronização

## Classes principais

| Classe | Papel |
|---|---|
| `ThreadApp` | Aplicação: três threads contando até 15 cada uma |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 09-concorrencia-redes-e-io/058-thread exec:java
```

Classe principal: `br.com.cidandrade.aulas.ThreadApp`. Na IDE, basta executar essa classe.

A saída é no console e muda a cada execução.

## Navegação

← [057 — Gráfico](../../08-arquitetura-e-persistencia/057-grafico) · [Sumário do curso](../../README.md#sumário) · [059 — Outra Thread](../../09-concorrencia-redes-e-io/059-outra-thread) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-058---Thread`](https://github.com/cidandrade/Codigo-058---Thread), hoje arquivado.
