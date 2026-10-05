# 026 — Estúdio

**Tema:** Padrão Factory Method (criacional)

## Objetivo

Aplicar o Factory Method: a classe base define o roteiro de contratação de um ensaio fotográfico, e cada estúdio (subclasse) decide qual ensaio concreto criar.

## Conceitos-chave

- Factory Method: método abstrato de criação (`criaEnsaio`) implementado pelas subclasses
- O código cliente depende só das abstrações (`Estudio`, `Ensaio`)
- Método que segue um roteiro fixo (`contrataEnsaio`) chamando o método de fábrica
- Enum para escolher o produto (`TipoEnsaio`)

## Classes principais

| Classe | Papel |
|---|---|
| `Estudio` | **Criador** abstrato: `contrataEnsaio` usa o método de fábrica `criaEnsaio` |
| `EstudioSaoPaulo`, `EstudioUbatuba` | **Criadores concretos**: cada um decide quais ensaios oferece |
| `Ensaio` | **Produto**: passos de preparação com comportamento padrão |
| `CatalogoSP`, `FemininoSP`, `MasculinoSP`, `EventoUbatuba`, `FemininoUbatuba`, `MasculinoUbatuba` | **Produtos concretos** (`CatalogoSP` personaliza os passos) |
| `TipoEnsaio` | Enum com os tipos de ensaio |
| `EstudioApp` | **Cliente**: contrata um ensaio de catálogo em São Paulo |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 05-padroes-criacionais/026-estudio exec:java
```

Classe principal: `br.com.cidandrade.aulas.EstudioApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [025 — CEP](../../04-interface-grafica-e-integracao/025-cep) · [Sumário do curso](../../README.md#sumário) · [027 — Estúdio melhorado](../../05-padroes-criacionais/027-estudio-melhorado) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-026---Estudio`](https://github.com/cidandrade/Codigo-026---Estudio), hoje arquivado.
