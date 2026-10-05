# 057 — Gráfico

**Tema:** Gráficos com JFreeChart

## Objetivo

Usar uma biblioteca externa (JFreeChart) para desenhar um gráfico de pizza com dados aleatórios dentro de uma janela Swing.

## Conceitos-chave

- Dependência externa gerenciada pelo Maven (`org.jfree:jfreechart`)
- `DefaultPieDataset` e `ChartFactory.createPieChart`
- `ChartPanel` como conteúdo do `JFrame`
- Configuração do gráfico pelo `PiePlot`

## Classes principais

| Classe | Papel |
|---|---|
| `GraficoGUI` | Janela que monta os dados e exibe o gráfico |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 08-arquitetura-e-persistencia/057-grafico exec:java
```

Classe principal: `br.com.cidandrade.aulas.gui.GraficoGUI`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Os valores mudam a cada execução.

## Navegação

← [056 — Livraria](../../08-arquitetura-e-persistencia/056-livraria) · [Sumário do curso](../../README.md#sumário) · [058 — Thread](../../09-concorrencia-redes-e-io/058-thread) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-057---Gr-fico`](https://github.com/cidandrade/Codigo-057---Gr-fico), hoje arquivado.
