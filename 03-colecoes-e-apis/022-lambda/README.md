# 022 — Lambda

**Tema:** Expressões lambda

## Objetivo

Chegar à expressão lambda passo a passo, filtrando uma lista de funcionários de quatro maneiras.

## Conceitos-chave

- Método 1: filtro fixo no código
- Método 2: classe que implementa uma interface de teste
- Método 3: classe anônima
- Método 4: expressão lambda
- Interface funcional (`ChecarFunc`)
- Enum aninhado (`Funcionario.Sexo`)

## Classes principais

| Classe | Papel |
|---|---|
| `ChecarFunc` | Interface funcional com o método `teste` |
| `Checagem` | Implementação nomeada do filtro (método 2) |
| `Funcionario` | Dados do funcionário; idade calculada em 01/01/1981 |
| `Lista` | Lista de funcionários de exemplo |
| `RHApp` | Aplicação: imprime no console o resultado dos quatro métodos |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 03-colecoes-e-apis/022-lambda exec:java
```

Classe principal: `br.com.cidandrade.aulas.RHApp`. Na IDE, basta executar essa classe.

## Navegação

← [021 — Datas](../../03-colecoes-e-apis/021-datas) · [Sumário do curso](../../README.md#sumário) · [023 — Quadrado](../../03-colecoes-e-apis/023-quadrado) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-022---Lambda`](https://github.com/cidandrade/Codigo-022---Lambda), hoje arquivado.
