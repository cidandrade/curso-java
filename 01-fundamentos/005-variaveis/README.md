# 005 — Variáveis

**Tema:** Variáveis e tipos de dados

## Objetivo

Apresentar os tipos primitivos do Java, `String` e `BigDecimal`, e as diferentes formas de escrever literais.

## Conceitos-chave

- Tipos primitivos: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`
- Literais: sufixos `L` e `F`, notação científica, `_` em números, hexadecimal (`0x`), binário (`0b`), Unicode (`\u0041`)
- Atributo × variável local × parâmetro
- `BigDecimal` para valores exatos

## Classes principais

| Classe | Papel |
|---|---|
| `SerHumano` | Atributos de vários tipos e métodos que os alteram |
| `SerHumanoApp` | Aplicação: inicializa um `SerHumano` e imprime idade e inicial |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/005-variaveis exec:java
```

Classe principal: `br.com.cidandrade.aulas.SerHumanoApp`. Na IDE, basta executar essa classe.

## Navegação

← [004 — Interface](../../01-fundamentos/004-interface) · [Sumário do curso](../../README.md#sumário) · [006 — Vetores](../../01-fundamentos/006-vetores) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-005---Variaveis`](https://github.com/cidandrade/Codigo-005---Variaveis), hoje arquivado.
