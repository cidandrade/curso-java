# 044 — Meteorologia GUI

**Tema:** Padrão Observer numa interface gráfica

## Objetivo

Ligar uma estação meteorológica a uma janela Swing pelo Observer: os botões alteram a estação, e a janela é avisada e atualiza o visor.

## Conceitos-chave

- Janela como observador (`JFrame implements PropertyChangeListener`)
- `JInternalFrame` (janelas internas) e menu
- Fluxo: botão → modelo → evento → visor
- Separação entre modelo (`Estacao`) e visão

## Classes principais

| Classe | Papel |
|---|---|
| `Estacao` | **Sujeito**: aumenta e diminui temperatura e umidade e dispara eventos |
| `CjtoDados` | Dados enviados no evento |
| `MetereologiaGUI` | **Observador**: janela com os controles e o visor |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/044-meteorologia-gui exec:java
```

Classe principal: `br.com.cidandrade.aulas.gui.MetereologiaGUI`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Use o menu para mostrar as janelas internas da estação e do visor. Para editar a janela no NetBeans, abra o `MetereologiaGUI.form`.

## Navegação

← [043 — Novo Meteorologia](../../07-padroes-comportamentais/043-novo-meteorologia) · [Sumário do curso](../../README.md#sumário) · [045 — Lâmpada](../../07-padroes-comportamentais/045-lampada) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-044---Metereologia-GUI`](https://github.com/cidandrade/Codigo-044---Metereologia-GUI), hoje arquivado.
