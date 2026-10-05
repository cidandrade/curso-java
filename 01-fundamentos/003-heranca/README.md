# 003 — Herança

**Tema:** Herança

## Objetivo

Mostrar que uma subclasse herda atributos e métodos da superclasse, mesmo sem declarar nada.

## Conceitos-chave

- `extends`
- Superclasse e subclasse
- Reaproveitamento de código por herança

## Classes principais

| Classe | Papel |
|---|---|
| `Carro` | Superclasse com o comportamento de um carro |
| `SUV` | Subclasse vazia de `Carro`: tudo o que faz vem por herança |
| `CarroApp` | Aplicação: usa um `Carro` e um `SUV` da mesma forma |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/003-heranca exec:java
```

Classe principal: `br.com.cidandrade.aulas.CarroApp`. Na IDE, basta executar essa classe.

## Navegação

← [002 — Carro](../../01-fundamentos/002-carro) · [Sumário do curso](../../README.md#sumário) · [004 — Interface](../../01-fundamentos/004-interface) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-003---Heranca`](https://github.com/cidandrade/Codigo-003---Heranca), hoje arquivado.
