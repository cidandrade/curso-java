# 017 — Enumeração

**Tema:** Enumerações (enum)

## Objetivo

Criar um `enum` com atributos, construtor e métodos.

## Conceitos-chave

- `enum` com construtor privado e atributos
- Métodos de instância num `enum`
- Constantes `static final`
- `values()` para percorrer as constantes

## Classes principais

| Classe | Papel |
|---|---|
| `Planeta` | Enum dos planetas com massa, raio e cálculo da gravidade na superfície |
| `PlanetasApp` | Aplicação: peso de uma pessoa de 80 kg em cada planeta |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/017-enumeracao exec:java
```

Classe principal: `br.com.cidandrade.aulas.PlanetasApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [016 — Matemática](../../02-recursos-da-linguagem/016-matematica) · [Sumário do curso](../../README.md#sumário) · [018 — Baralho com HashSet](../../03-colecoes-e-apis/018-baralho-com-hashset) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-017---Enumera--o`](https://github.com/cidandrade/Codigo-017---Enumera--o), hoje arquivado.
