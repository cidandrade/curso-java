# 042 — Meteorologia

**Tema:** Padrão Observer (comportamental)

## Objetivo

Fazer um dispositivo ser avisado sempre que os dados meteorológicos mudam, implementando o Observer com interfaces próprias.

## Conceitos-chave

- Observer: sujeito mantém uma lista de observadores e os notifica
- Registro e remoção de observadores
- Baixo acoplamento: o sujeito só conhece a interface `Observador`
- Modelo *push*: o sujeito envia os dados na notificação

## Classes principais

| Classe | Papel |
|---|---|
| `Sujeito` | Interface do **sujeito** (*subject*) |
| `Observador` | Interface do **observador**: `atualizar(...)` |
| `DadosMetereologicos` | **Sujeito concreto**: notifica a cada `setMedidas` |
| `Dispositivo` | **Observador concreto**: exibe temperatura e umidade |
| `ExibirElemento` | Interface de exibição |
| `Metereologia` | Aplicação: três medições, três notificações |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/042-meteorologia exec:java
```

Classe principal: `br.com.cidandrade.aulas.Metereologia`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [041 — Crédito](../../07-padroes-comportamentais/041-credito) · [Sumário do curso](../../README.md#sumário) · [043 — Novo Meteorologia](../../07-padroes-comportamentais/043-novo-meteorologia) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-042---Metereologia`](https://github.com/cidandrade/Codigo-042---Metereologia), hoje arquivado.
