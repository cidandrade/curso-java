# 043 — Novo Meteorologia

**Tema:** Padrão Observer com PropertyChangeSupport

## Objetivo

Refazer a aula 042 usando o suporte a Observer que já vem no Java (`java.beans`), sem escrever as interfaces à mão.

## Conceitos-chave

- `PropertyChangeSupport` e `firePropertyChange`
- `PropertyChangeListener` e `PropertyChangeEvent` (valor antigo e novo)
- Objeto de dados para enviar várias medidas num só evento (`CjtoDados`)
- Comparação com a implementação manual da aula 042

## Classes principais

| Classe | Papel |
|---|---|
| `DadosMetereologicos` | **Sujeito**: delega o registro e a notificação ao `PropertyChangeSupport` |
| `Dispositivo` | **Observador**: implementa `PropertyChangeListener` |
| `CjtoDados` | Conjunto de medidas enviado no evento |
| `Metereologia` | Aplicação de demonstração |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/043-novo-meteorologia exec:java
```

Classe principal: `br.com.cidandrade.aulas.Metereologia`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [042 — Meteorologia](../../07-padroes-comportamentais/042-meteorologia) · [Sumário do curso](../../README.md#sumário) · [044 — Meteorologia GUI](../../07-padroes-comportamentais/044-meteorologia-gui) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-043---Novo-Metereologia`](https://github.com/cidandrade/Codigo-043---Novo-Metereologia), hoje arquivado.
