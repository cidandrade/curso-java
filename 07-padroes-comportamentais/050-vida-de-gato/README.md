# 050 — Vida de Gato

**Tema:** Padrão State (comportamental)

## Objetivo

Modelar a rotina de um gato (dormir, ter fome, comer, brincar, tomar banho) com um objeto por estado, em vez de condicionais espalhadas.

## Conceitos-chave

- State: o comportamento muda conforme o objeto-estado atual
- Contexto que delega ao estado (`obterEstado`)
- Ação ao entrar num estado (`aoEntrarNoEstado`)
- Transições centralizadas no contexto (`tempoPassa`)

## Classes principais

| Classe | Papel |
|---|---|
| `Estado` | **Estado** (interface) |
| `EstadoImpl` | Estado abstrato com referência ao gato |
| `Dormindo`, `Faminto`, `Comendo`, `Brincando`, `Banhando` | **Estados concretos** |
| `Gato` | **Contexto**: guarda o estado atual e faz as transições |
| `VidaDeGatoApp` | **Cliente**: deixa o tempo passar cinco vezes |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/050-vida-de-gato exec:java
```

Classe principal: `br.com.cidandrade.aulas.VidaDeGatoApp`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [049 — Vida](../../07-padroes-comportamentais/049-vida) · [Sumário do curso](../../README.md#sumário) · [051 — Cães Polimórficos](../../07-padroes-comportamentais/051-caes-polimorficos) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-050---Vida-de-Gato`](https://github.com/cidandrade/Codigo-050---Vida-de-Gato), hoje arquivado.
