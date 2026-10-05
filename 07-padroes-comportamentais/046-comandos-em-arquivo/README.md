# 046 — Comandos em Arquivo

**Tema:** Padrão Command com referências a métodos

## Objetivo

Aplicar o Command a operações de arquivo (escrever e ler) e mostrar que, com interface funcional, uma referência a método já serve como comando.

## Conceitos-chave

- Interface de comando como interface funcional
- Referência a método como comando (`arquivo::escrever`)
- Invocador que guarda o histórico das operações
- NIO: `Files.write`, `Files.readAllLines`, `Paths`, `StandardCharsets.UTF_16`

## Classes principais

| Classe | Papel |
|---|---|
| `OperacaoEmArquivo` | **Command** (interface funcional) |
| `OpEscreverArquivo`, `OpLerArquivo` | **Comandos concretos** |
| `OperadorDeArquivo` | **Invocador**: executa e registra as operações |
| `Arquivo` | **Receptor**/fachada do arquivo |
| `ArquivoApp` | **Cliente**: escreve e lê `/tmp/comd.txt` |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/046-comandos-em-arquivo exec:java
```

Classe principal: `br.com.cidandrade.aulas.ArquivoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico. O caminho `/tmp/comd.txt` existe no Linux e no macOS; no Windows, altere-o para testar.

## Navegação

← [045 — Lâmpada](../../07-padroes-comportamentais/045-lampada) · [Sumário do curso](../../README.md#sumário) · [047 — Menus Diferentes](../../07-padroes-comportamentais/047-menus-diferentes) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-046---Comandos-em-Arquivo`](https://github.com/cidandrade/Codigo-046---Comandos-em-Arquivo), hoje arquivado.
