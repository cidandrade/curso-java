# E001 — Números primos

**Tema:** Exercício: laços e decisões

## Objetivo

Ler um número e dizer se ele é primo.

## Conceitos-chave

- Leitura com a biblioteca da aula 008 (`Entrada.getLong`)
- Filtro rápido: primos maiores que 3 têm a forma 6k ± 1
- Laço com condição de parada composta
- Operador `%` (resto da divisão)

## Classes principais

| Classe | Papel |
|---|---|
| `AchaPrimo` | Aplicação com a solução |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl exercicios/E001-numeros-primos exec:java
```

Classe principal: `br.com.cidandrade.aulas.extras.AchaPrimo`. Na IDE, basta executar essa classe.

Entrada e saída por caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [062 — Serialização de Objetos](../../09-concorrencia-redes-e-io/062-serializacao-de-objetos) · [Sumário do curso](../../README.md#sumário) · [E002 — Temperatura](../../exercicios/E002-temperatura) →

## Origem

Este código veio do repositório [`cidandrade/E001---N-meros-primos`](https://github.com/cidandrade/E001---N-meros-primos), hoje arquivado.
