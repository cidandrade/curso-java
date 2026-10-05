# 048 — Chat

**Tema:** Padrão Mediator (comportamental)

## Objetivo

Fazer os usuários de um chat conversarem por uma sala que intermedeia as mensagens, sem que um precise conhecer o outro.

## Conceitos-chave

- Mediator: os objetos se comunicam por um intermediário
- Colegas conhecem só o mediador, não uns aos outros
- Mensagem individual e para todos
- `Map` para localizar o destinatário pelo id

## Classes principais

| Classe | Papel |
|---|---|
| `SalaDeChatIface` | **Mediador** (interface) |
| `SalaDeChat` | **Mediador concreto**: entrega as mensagens |
| `Usuario` | **Colega** abstrato: conhece a sala |
| `UsuarioDeChat` | **Colega concreto**: envia e recebe mensagens |
| `ChatApp` | **Cliente**: quatro usuários trocando mensagens |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/048-chat exec:java
```

Classe principal: `br.com.cidandrade.aulas.ChatApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [047 — Menus Diferentes](../../07-padroes-comportamentais/047-menus-diferentes) · [Sumário do curso](../../README.md#sumário) · [049 — Vida](../../07-padroes-comportamentais/049-vida) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-048---Chat`](https://github.com/cidandrade/Codigo-048---Chat), hoje arquivado.
