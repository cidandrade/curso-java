# 056 — Livraria

**Tema:** Persistência com JDBC e MySQL; padrões DAO, Mediator e Singleton

## Objetivo

Construir um cadastro de livros com interface gráfica e banco MySQL, organizado em camadas: entidades, DAOs, um mediador entre os DAOs e um controle usado pela janela. A aplicação também informa em qual livraria está o melhor preço.

## Conceitos-chave

- JDBC: `DriverManager`, `Connection`, `Statement`, `ResultSet`, `ResultSetMetaData`
- DAO genérico (`DAO<T>`) com uma implementação por tabela
- Singleton para a conexão (`BD.obterBanco()`)
- Mediator para desacoplar os DAOs entre si
- Entidades com Lombok
- `JTable` com `DefaultTableModel`, `JComboBox`, abas e ícones carregados com `getResource`
- Consulta com subconsulta para o menor preço

## Classes principais

| Classe | Papel |
|---|---|
| `BD` | **Singleton** de acesso ao banco; `inicializa()` recria as tabelas com dados de exemplo |
| `DAO` | Interface genérica do **DAO** |
| `LivroDAO`, `CategoriaDAO`, `LivrariaDAO`, `PrecoDAO` | **DAOs** concretos com o SQL de cada tabela |
| `DAOMediator`, `Mediator` | **Mediador** entre os DAOs |
| `Util` | Atalhos para executar SQL pelo `BD` |
| `Controle` | Ponto de acesso da interface ao modelo (validações e chamadas aos DAOs) |
| `Livro`, `Categoria`, `Livraria`, `Preco` | **Entidades** |
| `LivrosGUI` | Janela: lista, inclui, altera e apaga livros e mostra o melhor preço |

## Como executar

### Banco de dados

A aplicação usa MySQL com URL, usuário e senha fixos em `BD.java`:

| Item | Valor |
|---|---|
| URL | `jdbc:mysql://localhost/livraria` |
| Usuário | `cidandrade` |
| Senha | `123456` |

O jeito mais simples de ter esse banco é com Docker. Nesta pasta, rode:

```bash
docker compose up -d     # sobe o MySQL 8 já com o banco livraria
```

Para desligar: `docker compose down`. Para também apagar os dados: `docker compose down -v`.

Sem Docker, crie num MySQL local o usuário `cidandrade` com senha `123456` e rode o script [`sql/livraria.sql`](sql/livraria.sql).

> **Atenção:** ao abrir, a aplicação chama `BD.inicializa()`, que **apaga e recria** as tabelas com os dados de exemplo. Alterações feitas numa execução não sobrevivem à seguinte.

### Executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 08-arquitetura-e-persistencia/056-livraria exec:java
```

Classe principal: `br.com.cidandrade.aulas.gui.LivrosGUI`. Na IDE, basta executar essa classe.

Abre uma janela Swing, por isso é preciso ambiente gráfico. Ao incluir um livro, a aplicação pergunta, em caixas de diálogo, o preço em cada livraria. Para editar a janela no NetBeans, abra o `LivrosGUI.form`.

## Navegação

← [055 — MVC](../../08-arquitetura-e-persistencia/055-mvc) · [Sumário do curso](../../README.md#sumário) · [057 — Gráfico](../../08-arquitetura-e-persistencia/057-grafico) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-056---Livraria`](https://github.com/cidandrade/Codigo-056---Livraria), hoje arquivado.
