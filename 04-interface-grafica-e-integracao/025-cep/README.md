# 025 — CEP

**Tema:** Consumo de serviço web (ViaCEP)

## Objetivo

Consultar um CEP no serviço web ViaCEP e preencher logradouro, bairro, cidade e UF numa janela Swing.

## Conceitos-chave

- `URL` e `URLConnection`
- Leitura de `InputStream` com `BufferedReader` e `lines()`
- Tratamento manual de uma resposta JSON com `replaceAll` e `split`
- Devolução dos dados num `Map<String, String>`
- Evento de teclado: a busca dispara quando o CEP tem 8 dígitos

## Classes principais

| Classe | Papel |
|---|---|
| `BuscaCEP` | Faz a requisição ao ViaCEP e extrai os campos do JSON |
| `BuscaCEPGUI` | Janela: campo de CEP e campos de resultado |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 04-interface-grafica-e-integracao/025-cep exec:java
```

Classe principal: `br.com.cidandrade.aulas.gui.BuscaCEPGUI`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Precisa de acesso à internet.

> **Exercício:** o código usa `http://viacep.com.br/ws/`. Altere para `https://` e observe que o restante continua igual. Outro exercício: substituir o tratamento manual do JSON por uma biblioteca como Jackson ou Gson.

## Navegação

← [024 — Calculadora](../../04-interface-grafica-e-integracao/024-calculadora) · [Sumário do curso](../../README.md#sumário) · [026 — Estúdio](../../05-padroes-criacionais/026-estudio) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-025---CEP`](https://github.com/cidandrade/Codigo-025---CEP), hoje arquivado.
