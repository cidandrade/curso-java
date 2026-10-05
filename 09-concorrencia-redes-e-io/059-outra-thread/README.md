# 059 — Outra Thread

**Tema:** Comunicação entre threads

## Objetivo

Fazer uma thread avisar outra de que deve parar, usando uma variável compartilhada de tipo atômico.

## Conceitos-chave

- `AtomicBoolean` em `java.util.concurrent.atomic`
- Thread que roda até receber um sinal
- `Thread.sleep`
- Classe anônima como `Runnable`
- Visibilidade de variáveis entre threads

## Classes principais

| Classe | Papel |
|---|---|
| `ThreadApp` | Aplicação: a thread 2 espera 3 ms e sinaliza; a thread 1 mostra até onde contou |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 09-concorrencia-redes-e-io/059-outra-thread exec:java
```

Classe principal: `br.com.cidandrade.aulas.ThreadApp`. Na IDE, basta executar essa classe.

A saída é no console e muda a cada execução.

## Navegação

← [058 — Thread](../../09-concorrencia-redes-e-io/058-thread) · [Sumário do curso](../../README.md#sumário) · [060 — Servidor TCP](../../09-concorrencia-redes-e-io/060-servidor-tcp) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-059---Outra-Thread`](https://github.com/cidandrade/Codigo-059---Outra-Thread), hoje arquivado.
