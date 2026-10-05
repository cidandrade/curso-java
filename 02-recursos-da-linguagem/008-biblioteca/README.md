# 008 — Biblioteca

**Tema:** Biblioteca de classes utilitárias reutilizável

## Objetivo

Montar uma biblioteca própria de classes utilitárias, reaproveitada pelas aulas seguintes, para não repetir código de entrada, saída, conversão e formatação.

## Conceitos-chave

- Reaproveitamento de código entre projetos
- Métodos `static` em classes utilitárias
- Sobrecarga de métodos
- `JOptionPane`, `DecimalFormat`, `java.time`
- Dependência entre módulos Maven (o artefato `br.com.cidandrade:biblioteca`)

## Classes principais

| Classe | Papel |
|---|---|
| `Entrada` | Lê `String`, `Float`, `Integer` e `Long` por caixa de diálogo; `confirma` faz perguntas sim/não |
| `Mensagem` | Mostra mensagens comuns e de erro em caixas de diálogo |
| `Conv` | Conversões entre `Object`, tipos numéricos, `String` e `byte[]` |
| `Data` | Formatação de datas (`dd/MM/yyyy` e formato SQL), idade e conversão `Date` → `LocalDate` |
| `Formatacao` | Formata números decimais com duas casas |
| `Matematica` | Números pseudoaleatórios dentro de uma faixa |

## Como executar

Este módulo não tem `main`: é uma biblioteca. O `mvn install` na raiz instala o artefato no repositório Maven local, e os módulos que dependem dele passam a encontrá-lo.

```bash
# na raiz do repositório
mvn -q install -DskipTests
```

## Navegação

← [007 — Entrada e Saída](../../01-fundamentos/007-entrada-e-saida) · [Sumário do curso](../../README.md#sumário) · [009 — Classes POJO](../../02-recursos-da-linguagem/009-classes-pojo) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-008---Biblioteca`](https://github.com/cidandrade/Codigo-008---Biblioteca), hoje arquivado.
