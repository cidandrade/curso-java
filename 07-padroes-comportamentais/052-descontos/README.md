# 052 — Descontos

**Tema:** Padrão Strategy com expressões lambda

## Objetivo

Implementar estratégias de desconto como lambdas, devolvidas por métodos estáticos de uma interface funcional.

## Conceitos-chave

- Strategy sem uma classe por estratégia
- Interface funcional com métodos `static` que devolvem lambdas
- Troca da estratégia em tempo de execução
- Comparação com a versão por classes da aula 051

## Classes principais

| Classe | Papel |
|---|---|
| `FormasDesconto` | **Estratégia** (interface funcional) e fábrica de estratégias: sem desconto, Natal, Ano-Novo e Páscoa |
| `Venda` | **Contexto**: `cobrar()` aplica o desconto atual |
| `DescontoApp` | **Cliente**: cobra sem desconto e com desconto de Natal |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/052-descontos exec:java
```

Classe principal: `br.com.cidandrade.aulas.DescontoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [051 — Cães Polimórficos](../../07-padroes-comportamentais/051-caes-polimorficos) · [Sumário do curso](../../README.md#sumário) · [053 — Chá ou Café](../../07-padroes-comportamentais/053-cha-ou-cafe) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-052---Descontos`](https://github.com/cidandrade/Codigo-052---Descontos), hoje arquivado.
