# 024 — Calculadora

**Tema:** Interface gráfica com Swing: calculadora RPN

## Objetivo

Construir no GUI Builder do NetBeans uma calculadora de notação polonesa reversa (RPN, como as HP), separando a lógica de cálculo da janela.

## Conceitos-chave

- `JFrame`, `JButton` e `JTextField` criados no GUI Builder (arquivo `.form`)
- Tratamento de eventos com `ActionListener`
- Separação entre lógica (`Calculadora`) e apresentação (`CalculadoraGUI`)
- Pilha de 4 níveis e tecla Enter (RPN)
- `BigDecimal` e `MathContext.DECIMAL128`
- Lambdas que implementam interfaces de operação unária e binária
- Enums aninhados e classe interna (`Pilha`)
- Caracteres Unicode nos botões (÷, √, ±)

## Classes principais

| Classe | Papel |
|---|---|
| `Calculadora` | Lógica da calculadora: visor, estado de entrada e erro |
| `Calculadora.Pilha` | Classe interna: pilha de 4 posições com as operações |
| `CalculadoraGUI` | Janela Swing: repassa cada botão para a `Calculadora` e atualiza o visor |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 04-interface-grafica-e-integracao/024-calculadora exec:java
```

Classe principal: `br.com.cidandrade.aulas.gui.CalculadoraGUI`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Para editar a janela no NetBeans, abra o `CalculadoraGUI.form`, que fica ao lado do `.java`. Para calcular 2 + 3, digite 2, Enter, 3, +.

## Navegação

← [023 — Quadrado](../../03-colecoes-e-apis/023-quadrado) · [Sumário do curso](../../README.md#sumário) · [025 — CEP](../../04-interface-grafica-e-integracao/025-cep) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-024---Calculadora`](https://github.com/cidandrade/Codigo-024---Calculadora), hoje arquivado.
