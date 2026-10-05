# 031 — Quem não tem cão

**Tema:** Padrão Adapter (estrutural)

## Objetivo

Fazer um gato "caçar com" uma matilha de cães: um adaptador deixa o `Gato` ser usado onde se espera um `Cachorro`. O nome vem do ditado "quem não tem cão caça com gato".

## Conceitos-chave

- Adapter de objeto: o adaptador implementa a interface-alvo e delega para o objeto adaptado
- Composição em vez de herança
- Operações sem equivalente no adaptado (`abanarRabo` lança `UnsupportedOperationException`)
- Polimorfismo: o laço trata cão e gato adaptado do mesmo jeito

## Classes principais

| Classe | Papel |
|---|---|
| `Cachorro` | **Alvo** (*target*): a interface que o cliente espera |
| `Gato` | Interface do **adaptado** (*adaptee*) |
| `Perdigueiro` | Implementação comum de `Cachorro` |
| `Siames` | Implementação de `Gato`: o objeto adaptado |
| `GatoAdaptador` | **Adaptador**: implementa `Cachorro` e repassa as chamadas ao `Gato` (`latir` → `miar`) |
| `CacadaApp` | **Cliente**: percorre a matilha `Cachorro[]` |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/031-quem-nao-tem-cao exec:java
```

Classe principal: `br.com.cidandrade.aulas.CacadaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [030 — Jogo de Cartas](../../05-padroes-criacionais/030-jogo-de-cartas) · [Sumário do curso](../../README.md#sumário) · [032 — Formas Coloridas](../../06-padroes-estruturais/032-formas-coloridas) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-031---Quem-n-o-tem-c-o`](https://github.com/cidandrade/Codigo-031---Quem-n-o-tem-c-o), hoje arquivado.
