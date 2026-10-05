# 055 — MVC

**Tema:** Arquitetura MVC (Model-View-Controller)

## Objetivo

Organizar uma aplicação Swing em três camadas (modelo, visão e controle), usando o Observer para a visão se atualizar quando o modelo muda.

## Conceitos-chave

- Modelo: dados e notificação de mudanças (`PropertyChangeSupport`)
- Visão: janela que mostra os dados e repassa as ações
- Controle: regras de validação antes de alterar o modelo (o controle funciona também como fachada)
- Observer ligando modelo e visão
- Pacotes por camada: `modelo`, `visao`, `controle`

## Classes principais

| Classe | Papel |
|---|---|
| `Pessoa` | **Modelo**: nome, profissão e religião; dispara eventos nos setters |
| `Controle` | **Controle**: valida as entradas (lista de profissões e religiões aceitas) e altera o modelo |
| `Visao` | **Visão**: `JFrame` que observa o modelo e chama o controle pelos botões |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 08-arquitetura-e-persistencia/055-mvc exec:java
```

Classe principal: `br.com.cidandrade.aulas.visao.Visao`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Para editar a janela no NetBeans, abra o `Visao.form`.

## Navegação

← [054 — Carrinho de Compras](../../07-padroes-comportamentais/054-carrinho-de-compras) · [Sumário do curso](../../README.md#sumário) · [056 — Livraria](../../08-arquitetura-e-persistencia/056-livraria) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-055---MVC`](https://github.com/cidandrade/Codigo-055---MVC), hoje arquivado.
