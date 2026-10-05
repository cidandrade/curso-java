# 045 — Lâmpada

**Tema:** Padrão Command (comportamental)

## Objetivo

Transformar "acender" e "apagar" em objetos-comando que um interruptor executa sem saber o que fazem.

## Conceitos-chave

- Command: encapsular uma ação num objeto
- Papéis: comando, comando concreto, invocador e receptor
- Troca do comando em tempo de execução (`setComando`)

## Classes principais

| Classe | Papel |
|---|---|
| `Comando` | **Command** (interface): `executar()` |
| `LigarLampada`, `DesligarLampada` | **Comandos concretos** |
| `Lampada` | **Receptor** (*receiver*): sabe ligar e desligar |
| `Interruptor` | **Invocador** (*invoker*): `aciona()` executa o comando atual |
| `LampadaApp` | **Cliente**: monta os comandos e aciona o interruptor |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/045-lampada exec:java
```

Classe principal: `br.com.cidandrade.aulas.LampadaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [044 — Meteorologia GUI](../../07-padroes-comportamentais/044-meteorologia-gui) · [Sumário do curso](../../README.md#sumário) · [046 — Comandos em Arquivo](../../07-padroes-comportamentais/046-comandos-em-arquivo) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-045---Lampada`](https://github.com/cidandrade/Codigo-045---Lampada), hoje arquivado.
