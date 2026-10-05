# 001 — Olá, Mundo

**Tema:** Primeiro programa em Java

## Objetivo

Escrever e executar o primeiro programa Java, que imprime uma mensagem na saída padrão.

## Conceitos-chave

- Estrutura mínima de uma classe Java
- Método `main(String[] args)` como ponto de entrada
- `System.out.println`
- Comentários Javadoc

## Classes principais

| Classe | Papel |
|---|---|
| `OlaMundo` | Aplicação: imprime "Olá, Mundo!!!" |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/001-ola-mundo exec:java
```

Classe principal: `br.com.cidandrade.aulas.OlaMundo`. Na IDE, basta executar essa classe.

## Navegação

[Sumário do curso](../../README.md#sumário) · [002 — Carro](../../01-fundamentos/002-carro) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-001---Ola-Mundo`](https://github.com/cidandrade/Codigo-001---Ola-Mundo), hoje arquivado.
