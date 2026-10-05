# 060 — Servidor TCP

**Tema:** Redes: servidor TCP com sockets

## Objetivo

Criar um servidor TCP que escuta a porta 12345 e devolve ao cliente a mensagem recebida, precedida de "Echo".

## Conceitos-chave

- `ServerSocket` e `accept()`
- `Socket` e seus fluxos de entrada e saída
- Envio de objetos com `ObjectOutputStream` e `ObjectInputStream`
- Uma classe `Thread` para atender cada cliente
- try-with-resources com sockets

## Classes principais

| Classe | Papel |
|---|---|
| `Servidor` | Escuta a porta 12345 e entrega cada conexão a um `ThreadCliente` |
| `ThreadCliente` | Lê a mensagem do cliente e responde "Echo ..." |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 09-concorrencia-redes-e-io/060-servidor-tcp exec:java
```

Classe principal: `br.com.cidandrade.aulas.Servidor`. Na IDE, basta executar essa classe.

O servidor fica rodando até ser interrompido (Ctrl+C). Com ele no ar, execute o cliente da aula [061](../061-cliente-tcp) em outro terminal.

## Navegação

← [059 — Outra Thread](../../09-concorrencia-redes-e-io/059-outra-thread) · [Sumário do curso](../../README.md#sumário) · [061 — Cliente TCP](../../09-concorrencia-redes-e-io/061-cliente-tcp) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-060---Servidor-TCP`](https://github.com/cidandrade/Codigo-060---Servidor-TCP), hoje arquivado.
