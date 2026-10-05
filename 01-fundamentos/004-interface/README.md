# 004 — Interface

**Tema:** Interfaces

## Objetivo

Definir um contrato com `interface` e implementá-lo numa classe, mantendo a herança do exemplo anterior.

## Conceitos-chave

- `interface` e `implements`
- `@Override`
- Contrato × implementação
- Interface combinada com herança

## Classes principais

| Classe | Papel |
|---|---|
| `InterfaceCarro` | Contrato: métodos que todo carro deve ter |
| `Carro` | Implementa `InterfaceCarro` |
| `SUV` | Herda de `Carro` e, por consequência, também cumpre o contrato |
| `CarroApp` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/004-interface exec:java
```

Classe principal: `br.com.cidandrade.aulas.CarroApp`. Na IDE, basta executar essa classe.

## Navegação

← [003 — Herança](../../01-fundamentos/003-heranca) · [Sumário do curso](../../README.md#sumário) · [005 — Variáveis](../../01-fundamentos/005-variaveis) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-004---Interface`](https://github.com/cidandrade/Codigo-004---Interface), hoje arquivado.
