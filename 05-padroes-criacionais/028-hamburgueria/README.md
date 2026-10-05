# 028 — Hamburgueria

**Tema:** Padrão Builder (criacional)

## Objetivo

Montar hambúrgueres com muitos ingredientes opcionais usando o Builder, em vez de construtores com dezenas de parâmetros.

## Conceitos-chave

- Builder como classe aninhada estática (`Hamburguer.Construtor`)
- Parâmetros obrigatórios no construtor do Builder; opcionais em métodos encadeados
- Interface fluente (cada método devolve `this`)
- Objeto final imutável (atributos `final`, construtor privado)
- A classe `Pedido` também usa interface fluente (`para(...).com(...)`)

## Classes principais

| Classe | Papel |
|---|---|
| `Hamburguer` | **Produto** imutável, com construtor privado |
| `Hamburguer.Construtor` | **Builder**: acumula as opções e chama `construir()` |
| `Hamburguer.TamanhoCarne` | Enum com o peso da carne |
| `Pedido` | Agrupa hambúrgueres com interface fluente |
| `ParaisoApp` | **Cliente**: monta um pedido com dois hambúrgueres |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 05-padroes-criacionais/028-hamburgueria exec:java
```

Classe principal: `br.com.cidandrade.aulas.ParaisoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [027 — Estúdio melhorado](../../05-padroes-criacionais/027-estudio-melhorado) · [Sumário do curso](../../README.md#sumário) · [029 — Nova Hamburgueria](../../05-padroes-criacionais/029-nova-hamburgueria) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-028---Hamburgeria`](https://github.com/cidandrade/Codigo-028---Hamburgeria), hoje arquivado.
