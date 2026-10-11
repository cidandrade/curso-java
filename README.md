# Curso de Java

[![Build](https://github.com/cidandrade/curso-java/actions/workflows/build.yml/badge.svg)](https://github.com/cidandrade/curso-java/actions/workflows/build.yml)
[![Licença: MIT](https://img.shields.io/badge/licen%C3%A7a-MIT-blue.svg)](LICENSE)

Aulas de orientação a objetos em Java do Prof. Cid Andrade.

Hoje uma IA escreve em segundos as classes que antes levavam horas. Este curso ensina o que ela não faz por você: **pensar o projeto**. O objetivo não é só aprender Java, e sim aprender a pensar em objetos para:

1. **reconhecer o problema de design** que o seu sistema tem;
2. **descrevê-lo com o vocabulário certo** (classes, responsabilidades, interfaces, padrões de projeto) num pedido à IA;
3. **revisar o código que a IA entrega** e saber se ele está bem projetado.

O Java é a linguagem de trabalho, porque deixa a orientação a objetos explícita: o que você aprende aqui vale para qualquer linguagem e qualquer assistente de IA.

São 62 aulas e 3 exercícios, cada um num módulo Maven independente com seu próprio README. Antes, cada aula ficava num repositório separado; a seção [Onde estava este código?](#onde-estava-este-código) mostra para onde foi cada um.

## Como usar este curso com uma IA

Comece pelo [guia](guia), que reúne o que vale para o curso todo:

1. [Pensar em objetos](guia/01-pensar-em-objetos.md): do enunciado às classes.
2. [Vocabulário de design](guia/02-vocabulario-de-design.md): os termos que fazem a IA entender o que você quer.
3. [Como pedir](guia/03-como-pedir.md): a anatomia de um bom pedido de projeto orientado a objetos.
4. [Como revisar](guia/04-como-revisar.md): o que conferir no código gerado.
5. [Mapa problema → padrão](guia/05-mapa-problema-padrao.md): do problema que você reconhece ao padrão que você pede.

Em cada aula, leia o código e rode o exemplo. Depois, peça a uma IA o mesmo desenho com suas próprias palavras e compare o resultado com o da aula. As aulas já revisadas trazem também o problema de design, um diagrama de classes, um exemplo de pedido e uma lista do que revisar.

## Pré-requisitos

- JDK 17 ou mais recente
- Maven 3.9 ou mais recente
- Ambiente gráfico para as aulas com janelas ou caixas de diálogo (a maioria delas)
- Para a aula 056 (Livraria): MySQL, ou Docker para subir o banco pronto

## Como executar

```bash
git clone https://github.com/cidandrade/curso-java.git
cd curso-java
mvn -q install -DskipTests                       # compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/001-ola-mundo exec:java # executa uma aula
```

Troque o caminho depois de `-pl` pelo da aula desejada. O README de cada aula traz o comando pronto.

**No NetBeans** (ou em outra IDE com suporte a Maven), abra a pasta raiz como projeto: os 65 módulos aparecem como subprojetos. As janelas feitas no GUI Builder continuam editáveis, pois os arquivos `.form` estão ao lado dos `.java`.

Muitas aulas usam a biblioteca utilitária da aula [008](02-recursos-da-linguagem/008-biblioteca) e o [Lombok](https://projectlombok.org/). O Maven baixa e configura o Lombok; na IDE, o processamento de anotações precisa estar ativo.

## Sumário

### [Fundamentos](01-fundamentos)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 001 | [Olá, Mundo](01-fundamentos/001-ola-mundo) | Primeiro programa em Java |
| 002 | [Carro](01-fundamentos/002-carro) | Classes e objetos |
| 003 | [Herança](01-fundamentos/003-heranca) | Herança |
| 004 | [Interface](01-fundamentos/004-interface) | Interfaces |
| 005 | [Variáveis](01-fundamentos/005-variaveis) | Variáveis e tipos de dados |
| 006 | [Vetores](01-fundamentos/006-vetores) | Vetores (arrays) |
| 007 | [Entrada e Saída](01-fundamentos/007-entrada-e-saida) | Entrada e saída: console, janelas e arquivo |

### [Recursos da linguagem](02-recursos-da-linguagem)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 008 | [Biblioteca](02-recursos-da-linguagem/008-biblioteca) | Biblioteca de classes utilitárias reutilizável |
| 009 | [Classes POJO](02-recursos-da-linguagem/009-classes-pojo) | POJO, classe abstrata e interface |
| 010 | [POJO com Lombok](02-recursos-da-linguagem/010-pojo-com-lombok) | Lombok |
| 011 | [String](02-recursos-da-linguagem/011-string) | A classe String |
| 012 | [Decisão e repetição](02-recursos-da-linguagem/012-decisao-e-repeticao) | Estruturas de decisão e de repetição |
| 013 | [Aninhamento de classes](02-recursos-da-linguagem/013-aninhamento-de-classes) | Classes aninhadas: interna e estática |
| 014 | [Classe aninhada local](02-recursos-da-linguagem/014-classe-aninhada-local) | Classe local |
| 015 | [Classe aninhada anônima](02-recursos-da-linguagem/015-classe-aninhada-anonima) | Classe anônima |
| 016 | [Matemática](02-recursos-da-linguagem/016-matematica) | A classe Math e precisão numérica |
| 017 | [Enumeração](02-recursos-da-linguagem/017-enumeracao) | Enumerações (enum) |

### [Coleções e APIs](03-colecoes-e-apis)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 018 | [Baralho com HashSet](03-colecoes-e-apis/018-baralho-com-hashset) | Coleções: Set e HashSet |
| 019 | [Baralho com List](03-colecoes-e-apis/019-baralho-com-list) | Coleções: List, ordenação e embaralhamento |
| 020 | [Menu com Map](03-colecoes-e-apis/020-menu-com-map) | Coleções: Map e HashMap |
| 021 | [Datas](03-colecoes-e-apis/021-datas) | API java.time |
| 022 | [Lambda](03-colecoes-e-apis/022-lambda) | Expressões lambda |
| 023 | [Quadrado](03-colecoes-e-apis/023-quadrado) | Generics |

### [Interface gráfica e integração](04-interface-grafica-e-integracao)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 024 | [Calculadora](04-interface-grafica-e-integracao/024-calculadora) | Interface gráfica com Swing: calculadora RPN |
| 025 | [CEP](04-interface-grafica-e-integracao/025-cep) | Consumo de serviço web (ViaCEP) |

### [Padrões de projeto criacionais](05-padroes-criacionais)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 026 | [Estúdio](05-padroes-criacionais/026-estudio) | Padrão Factory Method (criacional) |
| 027 | [Estúdio melhorado](05-padroes-criacionais/027-estudio-melhorado) | Padrão Abstract Factory (criacional), combinado com Factory Method |
| 028 | [Hamburgueria](05-padroes-criacionais/028-hamburgueria) | Padrão Builder (criacional) |
| 029 | [Nova Hamburgueria](05-padroes-criacionais/029-nova-hamburgueria) | Padrão Prototype (criacional) |
| 030 | [Jogo de Cartas](05-padroes-criacionais/030-jogo-de-cartas) | Padrão Singleton (criacional) |

### [Padrões de projeto estruturais](06-padroes-estruturais)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 031 | [Quem não tem cão](06-padroes-estruturais/031-quem-nao-tem-cao) | Padrão Adapter (estrutural) |
| 032 | [Formas Coloridas](06-padroes-estruturais/032-formas-coloridas) | Padrão Bridge (estrutural) |
| 033 | [Questionário](06-padroes-estruturais/033-questionario) | Padrão Bridge (estrutural) |
| 034 | [Empacotamento](06-padroes-estruturais/034-empacotamento) | Padrão Composite (estrutural) |
| 035 | [Cafeteria](06-padroes-estruturais/035-cafeteria) | Padrão Decorator (estrutural) |
| 036 | [Árvore de Natal](06-padroes-estruturais/036-arvore-de-natal) | Padrão Decorator (estrutural) |
| 037 | [Novo Formas Coloridas](06-padroes-estruturais/037-novo-formas-coloridas) | Padrão Facade (estrutural), sobre o Bridge da aula 032 |
| 038 | [Novo Carro](06-padroes-estruturais/038-novo-carro) | Padrão Facade (estrutural) |
| 039 | [Cafeteria Leve](06-padroes-estruturais/039-cafeteria-leve) | Padrão Flyweight (estrutural) |
| 040 | [Magos e Torres](06-padroes-estruturais/040-magos-e-torres) | Padrão Proxy (estrutural) |

### [Padrões de projeto comportamentais](07-padroes-comportamentais)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 041 | [Crédito](07-padroes-comportamentais/041-credito) | Padrão Chain of Responsibility (comportamental) |
| 042 | [Meteorologia](07-padroes-comportamentais/042-meteorologia) | Padrão Observer (comportamental) |
| 043 | [Novo Meteorologia](07-padroes-comportamentais/043-novo-meteorologia) | Padrão Observer com PropertyChangeSupport |
| 044 | [Meteorologia GUI](07-padroes-comportamentais/044-meteorologia-gui) | Padrão Observer numa interface gráfica |
| 045 | [Lâmpada](07-padroes-comportamentais/045-lampada) | Padrão Command (comportamental) |
| 046 | [Comandos em Arquivo](07-padroes-comportamentais/046-comandos-em-arquivo) | Padrão Command com referências a métodos |
| 047 | [Menus Diferentes](07-padroes-comportamentais/047-menus-diferentes) | Padrão Iterator (comportamental) |
| 048 | [Chat](07-padroes-comportamentais/048-chat) | Padrão Mediator (comportamental) |
| 049 | [Vida](07-padroes-comportamentais/049-vida) | Padrão Memento (comportamental) |
| 050 | [Vida de Gato](07-padroes-comportamentais/050-vida-de-gato) | Padrão State (comportamental) |
| 051 | [Cães Polimórficos](07-padroes-comportamentais/051-caes-polimorficos) | Padrão Strategy (comportamental) |
| 052 | [Descontos](07-padroes-comportamentais/052-descontos) | Padrão Strategy com expressões lambda |
| 053 | [Chá ou Café](07-padroes-comportamentais/053-cha-ou-cafe) | Padrão Template Method (comportamental) |
| 054 | [Carrinho de Compras](07-padroes-comportamentais/054-carrinho-de-compras) | Padrão Visitor (comportamental) |

### [Arquitetura e persistência](08-arquitetura-e-persistencia)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 055 | [MVC](08-arquitetura-e-persistencia/055-mvc) | Arquitetura MVC (Model-View-Controller) |
| 056 | [Livraria](08-arquitetura-e-persistencia/056-livraria) | Persistência com JDBC e MySQL; padrões DAO, Mediator e Singleton |
| 057 | [Gráfico](08-arquitetura-e-persistencia/057-grafico) | Gráficos com JFreeChart |

### [Concorrência, redes e E/S](09-concorrencia-redes-e-io)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| 058 | [Thread](09-concorrencia-redes-e-io/058-thread) | Threads |
| 059 | [Outra Thread](09-concorrencia-redes-e-io/059-outra-thread) | Comunicação entre threads |
| 060 | [Servidor TCP](09-concorrencia-redes-e-io/060-servidor-tcp) | Redes: servidor TCP com sockets |
| 061 | [Cliente TCP](09-concorrencia-redes-e-io/061-cliente-tcp) | Redes: cliente TCP com sockets |
| 062 | [Serialização de Objetos](09-concorrencia-redes-e-io/062-serializacao-de-objetos) | Serialização |

### [Exercícios](exercicios)

| Nº | Aula | Tema/Padrão |
|---|---|---|
| E001 | [Números primos](exercicios/E001-numeros-primos) | Exercício: laços e decisões |
| E002 | [Temperatura](exercicios/E002-temperatura) | Exercício: classes, getters e setters |
| E003 | [Triângulo](exercicios/E003-triangulo) | Exercício: decisões encadeadas |

## Onde estava este código?

Cada aula tinha um repositório próprio. Os repositórios antigos continuam no ar, arquivados, com um aviso apontando para cá.

| Repositório antigo | Pasta nova |
|---|---|
| [Codigo-001---Ola-Mundo](https://github.com/cidandrade/Codigo-001---Ola-Mundo) | [`01-fundamentos/001-ola-mundo`](01-fundamentos/001-ola-mundo) |
| [Codigo-002---Carro](https://github.com/cidandrade/Codigo-002---Carro) | [`01-fundamentos/002-carro`](01-fundamentos/002-carro) |
| [Codigo-003---Heranca](https://github.com/cidandrade/Codigo-003---Heranca) | [`01-fundamentos/003-heranca`](01-fundamentos/003-heranca) |
| [Codigo-004---Interface](https://github.com/cidandrade/Codigo-004---Interface) | [`01-fundamentos/004-interface`](01-fundamentos/004-interface) |
| [Codigo-005---Variaveis](https://github.com/cidandrade/Codigo-005---Variaveis) | [`01-fundamentos/005-variaveis`](01-fundamentos/005-variaveis) |
| [Codigo-006---Vetores](https://github.com/cidandrade/Codigo-006---Vetores) | [`01-fundamentos/006-vetores`](01-fundamentos/006-vetores) |
| [Codigo-007---Entrada-e-Saida](https://github.com/cidandrade/Codigo-007---Entrada-e-Saida) | [`01-fundamentos/007-entrada-e-saida`](01-fundamentos/007-entrada-e-saida) |
| [Codigo-008---Biblioteca](https://github.com/cidandrade/Codigo-008---Biblioteca) | [`02-recursos-da-linguagem/008-biblioteca`](02-recursos-da-linguagem/008-biblioteca) |
| [Codigo-009---Classes-POJO](https://github.com/cidandrade/Codigo-009---Classes-POJO) | [`02-recursos-da-linguagem/009-classes-pojo`](02-recursos-da-linguagem/009-classes-pojo) |
| [Codigo-010---Pojo-com-Lombok](https://github.com/cidandrade/Codigo-010---Pojo-com-Lombok) | [`02-recursos-da-linguagem/010-pojo-com-lombok`](02-recursos-da-linguagem/010-pojo-com-lombok) |
| [Codigo-011---String](https://github.com/cidandrade/Codigo-011---String) | [`02-recursos-da-linguagem/011-string`](02-recursos-da-linguagem/011-string) |
| [Codigo-012---Decisao-e-repeticao](https://github.com/cidandrade/Codigo-012---Decisao-e-repeticao) | [`02-recursos-da-linguagem/012-decisao-e-repeticao`](02-recursos-da-linguagem/012-decisao-e-repeticao) |
| [Codigo-013---Aninhamento-de-classes](https://github.com/cidandrade/Codigo-013---Aninhamento-de-classes) | [`02-recursos-da-linguagem/013-aninhamento-de-classes`](02-recursos-da-linguagem/013-aninhamento-de-classes) |
| [Codigo-014---Classe-aninhada-local](https://github.com/cidandrade/Codigo-014---Classe-aninhada-local) | [`02-recursos-da-linguagem/014-classe-aninhada-local`](02-recursos-da-linguagem/014-classe-aninhada-local) |
| [Codigo-015---Classe-aninhada-an-nima](https://github.com/cidandrade/Codigo-015---Classe-aninhada-an-nima) | [`02-recursos-da-linguagem/015-classe-aninhada-anonima`](02-recursos-da-linguagem/015-classe-aninhada-anonima) |
| [Codigo-016---Matematica](https://github.com/cidandrade/Codigo-016---Matematica) | [`02-recursos-da-linguagem/016-matematica`](02-recursos-da-linguagem/016-matematica) |
| [Codigo-017---Enumera--o](https://github.com/cidandrade/Codigo-017---Enumera--o) | [`02-recursos-da-linguagem/017-enumeracao`](02-recursos-da-linguagem/017-enumeracao) |
| [Codigo-018---Baralho-com-HashSet](https://github.com/cidandrade/Codigo-018---Baralho-com-HashSet) | [`03-colecoes-e-apis/018-baralho-com-hashset`](03-colecoes-e-apis/018-baralho-com-hashset) |
| [Codigo-019---Baralho-com-List](https://github.com/cidandrade/Codigo-019---Baralho-com-List) | [`03-colecoes-e-apis/019-baralho-com-list`](03-colecoes-e-apis/019-baralho-com-list) |
| [Codigo-020---Menu-com-Map](https://github.com/cidandrade/Codigo-020---Menu-com-Map) | [`03-colecoes-e-apis/020-menu-com-map`](03-colecoes-e-apis/020-menu-com-map) |
| [Codigo-021---Datas](https://github.com/cidandrade/Codigo-021---Datas) | [`03-colecoes-e-apis/021-datas`](03-colecoes-e-apis/021-datas) |
| [Codigo-022---Lambda](https://github.com/cidandrade/Codigo-022---Lambda) | [`03-colecoes-e-apis/022-lambda`](03-colecoes-e-apis/022-lambda) |
| [Codigo-023---Quadrado](https://github.com/cidandrade/Codigo-023---Quadrado) | [`03-colecoes-e-apis/023-quadrado`](03-colecoes-e-apis/023-quadrado) |
| [Codigo-024---Calculadora](https://github.com/cidandrade/Codigo-024---Calculadora) | [`04-interface-grafica-e-integracao/024-calculadora`](04-interface-grafica-e-integracao/024-calculadora) |
| [Codigo-025---CEP](https://github.com/cidandrade/Codigo-025---CEP) | [`04-interface-grafica-e-integracao/025-cep`](04-interface-grafica-e-integracao/025-cep) |
| [Codigo-026---Estudio](https://github.com/cidandrade/Codigo-026---Estudio) | [`05-padroes-criacionais/026-estudio`](05-padroes-criacionais/026-estudio) |
| [Codigo-027---Estudio-Melhorado](https://github.com/cidandrade/Codigo-027---Estudio-Melhorado) | [`05-padroes-criacionais/027-estudio-melhorado`](05-padroes-criacionais/027-estudio-melhorado) |
| [Codigo-028---Hamburgeria](https://github.com/cidandrade/Codigo-028---Hamburgeria) | [`05-padroes-criacionais/028-hamburgueria`](05-padroes-criacionais/028-hamburgueria) |
| [Codigo-029---Nova-Hamburgueria](https://github.com/cidandrade/Codigo-029---Nova-Hamburgueria) | [`05-padroes-criacionais/029-nova-hamburgueria`](05-padroes-criacionais/029-nova-hamburgueria) |
| [Codigo-030---Jogo-de-Cartas](https://github.com/cidandrade/Codigo-030---Jogo-de-Cartas) | [`05-padroes-criacionais/030-jogo-de-cartas`](05-padroes-criacionais/030-jogo-de-cartas) |
| [Codigo-031---Quem-n-o-tem-c-o](https://github.com/cidandrade/Codigo-031---Quem-n-o-tem-c-o) | [`06-padroes-estruturais/031-quem-nao-tem-cao`](06-padroes-estruturais/031-quem-nao-tem-cao) |
| [Codigo-032---Formas-Coloridas](https://github.com/cidandrade/Codigo-032---Formas-Coloridas) | [`06-padroes-estruturais/032-formas-coloridas`](06-padroes-estruturais/032-formas-coloridas) |
| [Codigo-033---Question-rio](https://github.com/cidandrade/Codigo-033---Question-rio) | [`06-padroes-estruturais/033-questionario`](06-padroes-estruturais/033-questionario) |
| [Codigo-034---Empacotamento](https://github.com/cidandrade/Codigo-034---Empacotamento) | [`06-padroes-estruturais/034-empacotamento`](06-padroes-estruturais/034-empacotamento) |
| [Codigo-035---Cafeteria](https://github.com/cidandrade/Codigo-035---Cafeteria) | [`06-padroes-estruturais/035-cafeteria`](06-padroes-estruturais/035-cafeteria) |
| [Codigo-036----rvore-de-Natal](https://github.com/cidandrade/Codigo-036----rvore-de-Natal) | [`06-padroes-estruturais/036-arvore-de-natal`](06-padroes-estruturais/036-arvore-de-natal) |
| [Codigo-037---Novo-Formas-Coloridas](https://github.com/cidandrade/Codigo-037---Novo-Formas-Coloridas) | [`06-padroes-estruturais/037-novo-formas-coloridas`](06-padroes-estruturais/037-novo-formas-coloridas) |
| [Codigo-038---Novo-Carro](https://github.com/cidandrade/Codigo-038---Novo-Carro) | [`06-padroes-estruturais/038-novo-carro`](06-padroes-estruturais/038-novo-carro) |
| [Codigo-039---Cafeteria-Leve](https://github.com/cidandrade/Codigo-039---Cafeteria-Leve) | [`06-padroes-estruturais/039-cafeteria-leve`](06-padroes-estruturais/039-cafeteria-leve) |
| [Codigo-040---Magos-e-Torres](https://github.com/cidandrade/Codigo-040---Magos-e-Torres) | [`06-padroes-estruturais/040-magos-e-torres`](06-padroes-estruturais/040-magos-e-torres) |
| [Codigo-041---Credito](https://github.com/cidandrade/Codigo-041---Credito) | [`07-padroes-comportamentais/041-credito`](07-padroes-comportamentais/041-credito) |
| [Codigo-042---Metereologia](https://github.com/cidandrade/Codigo-042---Metereologia) | [`07-padroes-comportamentais/042-meteorologia`](07-padroes-comportamentais/042-meteorologia) |
| [Codigo-043---Novo-Metereologia](https://github.com/cidandrade/Codigo-043---Novo-Metereologia) | [`07-padroes-comportamentais/043-novo-meteorologia`](07-padroes-comportamentais/043-novo-meteorologia) |
| [Codigo-044---Metereologia-GUI](https://github.com/cidandrade/Codigo-044---Metereologia-GUI) | [`07-padroes-comportamentais/044-meteorologia-gui`](07-padroes-comportamentais/044-meteorologia-gui) |
| [Codigo-045---Lampada](https://github.com/cidandrade/Codigo-045---Lampada) | [`07-padroes-comportamentais/045-lampada`](07-padroes-comportamentais/045-lampada) |
| [Codigo-046---Comandos-em-Arquivo](https://github.com/cidandrade/Codigo-046---Comandos-em-Arquivo) | [`07-padroes-comportamentais/046-comandos-em-arquivo`](07-padroes-comportamentais/046-comandos-em-arquivo) |
| [Codigo-047---Menus-Diferentes](https://github.com/cidandrade/Codigo-047---Menus-Diferentes) | [`07-padroes-comportamentais/047-menus-diferentes`](07-padroes-comportamentais/047-menus-diferentes) |
| [Codigo-048---Chat](https://github.com/cidandrade/Codigo-048---Chat) | [`07-padroes-comportamentais/048-chat`](07-padroes-comportamentais/048-chat) |
| [Codigo-049---Vida](https://github.com/cidandrade/Codigo-049---Vida) | [`07-padroes-comportamentais/049-vida`](07-padroes-comportamentais/049-vida) |
| [Codigo-050---Vida-de-Gato](https://github.com/cidandrade/Codigo-050---Vida-de-Gato) | [`07-padroes-comportamentais/050-vida-de-gato`](07-padroes-comportamentais/050-vida-de-gato) |
| [Codigo-051---C-es-Polim-rficos](https://github.com/cidandrade/Codigo-051---C-es-Polim-rficos) | [`07-padroes-comportamentais/051-caes-polimorficos`](07-padroes-comportamentais/051-caes-polimorficos) |
| [Codigo-052---Descontos](https://github.com/cidandrade/Codigo-052---Descontos) | [`07-padroes-comportamentais/052-descontos`](07-padroes-comportamentais/052-descontos) |
| [Codigo-053---Ch--ou-Caf-](https://github.com/cidandrade/Codigo-053---Ch--ou-Caf-) | [`07-padroes-comportamentais/053-cha-ou-cafe`](07-padroes-comportamentais/053-cha-ou-cafe) |
| [Codigo-054---Carrinho-de-Compras](https://github.com/cidandrade/Codigo-054---Carrinho-de-Compras) | [`07-padroes-comportamentais/054-carrinho-de-compras`](07-padroes-comportamentais/054-carrinho-de-compras) |
| [Codigo-055---MVC](https://github.com/cidandrade/Codigo-055---MVC) | [`08-arquitetura-e-persistencia/055-mvc`](08-arquitetura-e-persistencia/055-mvc) |
| [Codigo-056---Livraria](https://github.com/cidandrade/Codigo-056---Livraria) | [`08-arquitetura-e-persistencia/056-livraria`](08-arquitetura-e-persistencia/056-livraria) |
| [Codigo-057---Gr-fico](https://github.com/cidandrade/Codigo-057---Gr-fico) | [`08-arquitetura-e-persistencia/057-grafico`](08-arquitetura-e-persistencia/057-grafico) |
| [Codigo-058---Thread](https://github.com/cidandrade/Codigo-058---Thread) | [`09-concorrencia-redes-e-io/058-thread`](09-concorrencia-redes-e-io/058-thread) |
| [Codigo-059---Outra-Thread](https://github.com/cidandrade/Codigo-059---Outra-Thread) | [`09-concorrencia-redes-e-io/059-outra-thread`](09-concorrencia-redes-e-io/059-outra-thread) |
| [Codigo-060---Servidor-TCP](https://github.com/cidandrade/Codigo-060---Servidor-TCP) | [`09-concorrencia-redes-e-io/060-servidor-tcp`](09-concorrencia-redes-e-io/060-servidor-tcp) |
| [Codigo-061---Cliente-TCP](https://github.com/cidandrade/Codigo-061---Cliente-TCP) | [`09-concorrencia-redes-e-io/061-cliente-tcp`](09-concorrencia-redes-e-io/061-cliente-tcp) |
| [Codigo-062---Serializa--o-de-Objetos](https://github.com/cidandrade/Codigo-062---Serializa--o-de-Objetos) | [`09-concorrencia-redes-e-io/062-serializacao-de-objetos`](09-concorrencia-redes-e-io/062-serializacao-de-objetos) |
| [E001---N-meros-primos](https://github.com/cidandrade/E001---N-meros-primos) | [`exercicios/E001-numeros-primos`](exercicios/E001-numeros-primos) |
| [E002---Temperatura](https://github.com/cidandrade/E002---Temperatura) | [`exercicios/E002-temperatura`](exercicios/E002-temperatura) |
| [Extra-003---Tri-ngulo](https://github.com/cidandrade/Extra-003---Tri-ngulo) | [`exercicios/E003-triangulo`](exercicios/E003-triangulo) |

O histórico de commits dos repositórios antigos foi preservado neste repositório.

## Licença

Distribuído sob a licença MIT. Veja o arquivo [LICENSE](LICENSE).
