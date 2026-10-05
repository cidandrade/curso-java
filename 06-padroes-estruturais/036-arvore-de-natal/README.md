# 036 — Árvore de Natal

**Tema:** Padrão Decorator (estrutural)

## Objetivo

Fixar o Decorator com um exemplo curto: enfeites que envolvem uma árvore de Natal.

## Conceitos-chave

- Decorador abstrato que guarda o componente e delega para ele
- Decoradores concretos que acrescentam comportamento antes ou depois da delegação
- O mesmo decorador pode ser aplicado mais de uma vez

## Classes principais

| Classe | Papel |
|---|---|
| `DecoracaoArvoreDeNatal` | **Componente** (interface) |
| `ArvoreDeNatal` | **Componente concreto** |
| `Decoracao` | **Decorador** abstrato |
| `Guirlanda`, `Luzinhas` | **Decoradores concretos** |
| `NatalApp` | **Cliente**: árvore com luzinhas e duas guirlandas |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/036-arvore-de-natal exec:java
```

Classe principal: `br.com.cidandrade.aulas.NatalApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [035 — Cafeteria](../../06-padroes-estruturais/035-cafeteria) · [Sumário do curso](../../README.md#sumário) · [037 — Novo Formas Coloridas](../../06-padroes-estruturais/037-novo-formas-coloridas) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-036----rvore-de-Natal`](https://github.com/cidandrade/Codigo-036----rvore-de-Natal), hoje arquivado.
