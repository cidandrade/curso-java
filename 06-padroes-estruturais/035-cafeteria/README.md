# 035 — Cafeteria

**Tema:** Padrão Decorator (estrutural)

## Objetivo

Acrescentar adicionais (chantilly, bits de chocolate, leite vaporizado) a um café em tempo de execução, somando preço e descrição camada por camada.

## Conceitos-chave

- Decorator: o decorador é do mesmo tipo do objeto que envolve
- Composição recursiva: `new Chantilly(new BitsDeChocolate(cafe))`
- Alternativa a criar uma subclasse para cada combinação
- Preço que depende do tamanho (enum `Tamanho`)

## Classes principais

| Classe | Papel |
|---|---|
| `Cafe` | **Componente** abstrato: descrição, tamanho e `preco()` |
| `Expresso`, `ExtraForte`, `CafeDaCasa` | **Componentes concretos** |
| `Adicional` | **Decorador** abstrato: estende `Cafe` |
| `Chantilly`, `BitsDeChocolate`, `LeiteVaporizado` | **Decoradores concretos**: somam seu preço ao do café envolvido |
| `Cafeteria` | **Cliente**: monta três cafés com adicionais |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/035-cafeteria exec:java
```

Classe principal: `br.com.cidandrade.aulas.Cafeteria`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [034 — Empacotamento](../../06-padroes-estruturais/034-empacotamento) · [Sumário do curso](../../README.md#sumário) · [036 — Árvore de Natal](../../06-padroes-estruturais/036-arvore-de-natal) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-035---Cafeteria`](https://github.com/cidandrade/Codigo-035---Cafeteria), hoje arquivado.
