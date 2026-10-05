# 027 — Estúdio melhorado

**Tema:** Padrão Abstract Factory (criacional), combinado com Factory Method

## Objetivo

Evoluir o estúdio da aula 026: cada cidade passa a ter uma fábrica de itens (fundo, figurino, acessório, ambiente) que produz uma família coerente de objetos.

## Conceitos-chave

- Abstract Factory: uma interface que cria uma família de produtos relacionados
- Fábricas concretas por contexto (São Paulo × Ubatuba)
- Factory Method (da aula 026) e Abstract Factory trabalhando juntos
- Injeção da fábrica pelo construtor do ensaio

## Classes principais

| Classe | Papel |
|---|---|
| `ItensDoEnsaio` | **Fábrica abstrata**: `preparaFundo`, `separaFigurino`, `separaAcessorios`, `preparaAmbiente` |
| `PreparativosSP`, `PreparativosUbatuba` | **Fábricas concretas**: produzem os itens de cada cidade |
| `Fundo`, `Figurino`, `Acessorio`, `Ambiente` | **Produtos abstratos** |
| `FundoBranco`, `FundoTexturizado`, `FigurinoVintage`, `SaidaDePraia`, `AcessorioVintage`, `Concha`, `MusicaLeve`, `VinhoBranco` | **Produtos concretos** |
| `Estudio`, `EstudioSaoPaulo`, `EstudioUbatuba` | Factory Method da aula 026, agora entregando a fábrica certa a cada ensaio |
| `Ensaio` | Ensaio abstrato com os quatro itens e o método `preparar()` |
| `CatalogoSP`, `FemininoSP`, `MasculinoSP`, `EventoUbatuba`, `FemininoUbatuba`, `MasculinoUbatuba` | **Clientes** da fábrica: `preparar()` pede cada item a ela |
| `EstudioApp` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 05-padroes-criacionais/027-estudio-melhorado exec:java
```

Classe principal: `br.com.cidandrade.aulas.EstudioApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [026 — Estúdio](../../05-padroes-criacionais/026-estudio) · [Sumário do curso](../../README.md#sumário) · [028 — Hamburgueria](../../05-padroes-criacionais/028-hamburgueria) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-027---Estudio-Melhorado`](https://github.com/cidandrade/Codigo-027---Estudio-Melhorado), hoje arquivado.
