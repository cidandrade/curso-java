# 051 — Cães Polimórficos

**Tema:** Padrão Strategy (comportamental)

## Objetivo

Dar a um cão comportamentos de dieta e de latido intercambiáveis, trocáveis em tempo de execução.

## Conceitos-chave

- Strategy: famílias de algoritmos encapsulados e intercambiáveis
- Composição em vez de herança
- Troca da estratégia com setter (`setDieta`)
- Programar para a interface, não para a implementação

## Classes principais

| Classe | Papel |
|---|---|
| `ComportDieta`, `ComportLatido` | **Estratégias** (interfaces) |
| `DietaNormal`, `DietaProteina` | **Estratégias concretas** de dieta |
| `LatidoBrincalhao`, `Rosnado`, `Mudo` | **Estratégias concretas** de latido |
| `Cao` | **Contexto** abstrato: delega `comer` e `latir` |
| `Labrador` | Contexto concreto com estratégias iniciais |
| `CaesApp` | **Cliente**: troca a dieta do labrador |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/051-caes-polimorficos exec:java
```

Classe principal: `br.com.cidandrade.aulas.CaesApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [050 — Vida de Gato](../../07-padroes-comportamentais/050-vida-de-gato) · [Sumário do curso](../../README.md#sumário) · [052 — Descontos](../../07-padroes-comportamentais/052-descontos) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-051---C-es-Polim-rficos`](https://github.com/cidandrade/Codigo-051---C-es-Polim-rficos), hoje arquivado.
