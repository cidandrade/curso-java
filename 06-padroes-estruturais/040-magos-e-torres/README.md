# 040 — Magos e Torres

**Tema:** Padrão Proxy (estrutural)

## Objetivo

Controlar o acesso a uma torre mágica: o proxy deixa entrar só os três primeiros magos.

## Conceitos-chave

- Proxy de proteção: mesma interface do objeto real, com controle de acesso
- O proxy guarda a referência ao objeto real e decide quando delegar
- Para o cliente, proxy e torre real são intercambiáveis

## Classes principais

| Classe | Papel |
|---|---|
| `TorreDaMagia` | **Sujeito** (interface) |
| `TorreDeMarfim` | **Sujeito real** |
| `TorreDaMagiaProxy` | **Proxy**: conta as entradas e barra a partir do quarto mago |
| `Mago` | Quem tenta entrar |
| `MagoApp` | **Cliente**: cinco magos tentam entrar |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/040-magos-e-torres exec:java
```

Classe principal: `br.com.cidandrade.aulas.MagoApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [039 — Cafeteria Leve](../../06-padroes-estruturais/039-cafeteria-leve) · [Sumário do curso](../../README.md#sumário) · [041 — Crédito](../../07-padroes-comportamentais/041-credito) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-040---Magos-e-Torres`](https://github.com/cidandrade/Codigo-040---Magos-e-Torres), hoje arquivado.
