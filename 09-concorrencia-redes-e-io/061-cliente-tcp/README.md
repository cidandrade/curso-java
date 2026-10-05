# 061 — Cliente TCP

**Tema:** Redes: cliente TCP com sockets

## Objetivo

Conectar ao servidor da aula 060, enviar "Oi" e mostrar a resposta.

## Conceitos-chave

- `Socket("localhost", 12345)`
- Envio e recebimento de objetos pelos fluxos do socket
- Tratamento de `IOException` e `ClassNotFoundException`

## Classes principais

| Classe | Papel |
|---|---|
| `Cliente` | Envia a mensagem e imprime a resposta |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 09-concorrencia-redes-e-io/061-cliente-tcp exec:java
```

Classe principal: `br.com.cidandrade.aulas.Cliente`. Na IDE, basta executar essa classe.

Execute antes o servidor da aula [060](../060-servidor-tcp), em outro terminal. A resposta esperada é `Echo Oi`.

## Navegação

← [060 — Servidor TCP](../../09-concorrencia-redes-e-io/060-servidor-tcp) · [Sumário do curso](../../README.md#sumário) · [062 — Serialização de Objetos](../../09-concorrencia-redes-e-io/062-serializacao-de-objetos) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-061---Cliente-TCP`](https://github.com/cidandrade/Codigo-061---Cliente-TCP), hoje arquivado.
