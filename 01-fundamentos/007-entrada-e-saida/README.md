# 007 — Entrada e Saída

**Tema:** Entrada e saída: console, janelas e arquivo

## Objetivo

Ler e escrever dados de três maneiras: no console com `Scanner`, em caixas de diálogo com `JOptionPane` e em arquivo texto.

## Conceitos-chave

- `Scanner` sobre `System.in`
- `JOptionPane` (`showInputDialog`, `showMessageDialog`)
- `FileWriter` e leitura de arquivo com `Scanner`
- try-with-resources
- Tratamento de exceções (`InputMismatchException`, `NumberFormatException`, `IOException`)
- Conversão de texto: `Byte.parseByte`, `Float.parseFloat`

## Classes principais

| Classe | Papel |
|---|---|
| `IOTexto` | Entrada e saída pelo console |
| `IOGrafico` | Entrada e saída por caixas de diálogo |
| `IOArquivo` | Grava e lê `/tmp/testeJava.txt` |
| `Mensagem` | Atalhos para os diálogos do `JOptionPane` |
| `EntradaSaidaApp` | Aplicação: executa os três modos em sequência |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/007-entrada-e-saida exec:java
```

Classe principal: `br.com.cidandrade.aulas.EntradaSaidaApp`. Na IDE, basta executar essa classe.

Primeiro o programa conversa pelo console e depois abre caixas de diálogo, por isso precisa de ambiente gráfico. O caminho `/tmp/testeJava.txt` existe no Linux e no macOS; no Windows, altere-o para testar.

## Navegação

← [006 — Vetores](../../01-fundamentos/006-vetores) · [Sumário do curso](../../README.md#sumário) · [008 — Biblioteca](../../02-recursos-da-linguagem/008-biblioteca) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-007---Entrada-e-Saida`](https://github.com/cidandrade/Codigo-007---Entrada-e-Saida), hoje arquivado.
