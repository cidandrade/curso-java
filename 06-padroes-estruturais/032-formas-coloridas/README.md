# 032 — Formas Coloridas

**Tema:** Padrão Bridge (estrutural)

## Objetivo

Separar duas dimensões que variam de forma independente (a forma geométrica e a cor), ligando-as por uma "ponte" em vez de criar uma subclasse para cada combinação.

## Conceitos-chave

- Bridge: abstração e implementação em hierarquias separadas
- Composição: a `Forma` tem uma `Cor_Ponte`
- Evita a explosão de subclasses (`QuadradoAzul`, `QuadradoVerde`, `HexagonoAzul`...)

## Classes principais

| Classe | Papel |
|---|---|
| `Forma` | **Abstração**: guarda a referência para a cor |
| `Quadrado`, `Hexagono` | **Abstrações refinadas** |
| `Cor_Ponte` | **Implementador**: a interface da ponte |
| `Azul`, `Verde` | **Implementadores concretos** |
| `FormaApp` | **Cliente**: cria um quadrado azul |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/032-formas-coloridas exec:java
```

Classe principal: `br.com.cidandrade.aulas.FormaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [031 — Quem não tem cão](../../06-padroes-estruturais/031-quem-nao-tem-cao) · [Sumário do curso](../../README.md#sumário) · [033 — Questionário](../../06-padroes-estruturais/033-questionario) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-032---Formas-Coloridas`](https://github.com/cidandrade/Codigo-032---Formas-Coloridas), hoje arquivado.
