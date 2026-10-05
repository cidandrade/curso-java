# 009 — Classes POJO

**Tema:** POJO, classe abstrata e interface

## Objetivo

Escrever classes POJO completas à mão (construtores, getters, setters, `equals`, `hashCode` e `toString`) numa hierarquia com interface e classe abstrata.

## Conceitos-chave

- POJO (*Plain Old Java Object*)
- Classe abstrata que implementa uma interface
- Sobrescrita de `equals`, `hashCode` e `toString`
- Uso da biblioteca da aula 008
- `try`/`catch`/`finally` com `NumberFormatException`

## Classes principais

| Classe | Papel |
|---|---|
| `GeometriaInterfaces` | Contrato: `getArea` e `getDescricao` |
| `FormaGeometrica` | Classe abstrata base das formas |
| `Triangulo` | POJO com base e altura; área = base × altura / 2 |
| `Retangulo` | POJO com base e altura; área = base × altura |
| `PojoApp` | Aplicação: lê duas medidas e descreve as duas formas |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/009-classes-pojo exec:java
```

Classe principal: `br.com.cidandrade.aulas.PojoApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [008 — Biblioteca](../../02-recursos-da-linguagem/008-biblioteca) · [Sumário do curso](../../README.md#sumário) · [010 — POJO com Lombok](../../02-recursos-da-linguagem/010-pojo-com-lombok) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-009---Classes-POJO`](https://github.com/cidandrade/Codigo-009---Classes-POJO), hoje arquivado.
