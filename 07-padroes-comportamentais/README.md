# Padrões de projeto comportamentais

Problemas de **divisão de responsabilidades** entre objetos que conversam: quando pedir uma cadeia de tratadores, um mecanismo de notificação, ações guardadas como objetos, um percurso uniforme, um intermediário, um histórico de estados, estados com comportamento próprio, algoritmos intercambiáveis, um roteiro com etapas variáveis ou operações separadas da estrutura.

| Nº | Aula | Tema |
|---|---|---|
| 041 | [Crédito](041-credito) | Padrão Chain of Responsibility (comportamental) |
| 042 | [Meteorologia](042-meteorologia) | Padrão Observer (comportamental) |
| 043 | [Novo Meteorologia](043-novo-meteorologia) | Padrão Observer com PropertyChangeSupport |
| 044 | [Meteorologia GUI](044-meteorologia-gui) | Padrão Observer numa interface gráfica |
| 045 | [Lâmpada](045-lampada) | Padrão Command (comportamental) |
| 046 | [Comandos em Arquivo](046-comandos-em-arquivo) | Padrão Command com referências a métodos |
| 047 | [Menus Diferentes](047-menus-diferentes) | Padrão Iterator (comportamental) |
| 048 | [Chat](048-chat) | Padrão Mediator (comportamental) |
| 049 | [Vida](049-vida) | Padrão Memento (comportamental) |
| 050 | [Vida de Gato](050-vida-de-gato) | Padrão State (comportamental) |
| 051 | [Cães Polimórficos](051-caes-polimorficos) | Padrão Strategy (comportamental) |
| 052 | [Descontos](052-descontos) | Padrão Strategy com expressões lambda |
| 053 | [Chá ou Café](053-cha-ou-cafe) | Padrão Template Method (comportamental) |
| 054 | [Carrinho de Compras](054-carrinho-de-compras) | Padrão Visitor (comportamental) |

## Se você precisa…, peça um…

Use esta tabela para reconhecer o problema e saber que nome dar a ele num pedido à IA. O mapa de todos os padrões está no [guia](../guia/05-mapa-problema-padrao.md).

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| Um pedido passa por vários tratadores até que um deles resolva | **Chain of Responsibility** | [041](041-credito) |
| Vários objetos precisam saber quando outro muda | **Observer** | [042](042-meteorologia), [043](043-novo-meteorologia), [044](044-meteorologia-gui) |
| Guardar ações como objetos para executar, enfileirar, registrar ou desfazer | **Command** | [045](045-lampada), [046](046-comandos-em-arquivo) |
| Percorrer coleções diferentes do mesmo jeito, sem expor como são guardadas | **Iterator** | [047](047-menus-diferentes) |
| Muitos objetos conversam entre si e o acoplamento virou uma teia | **Mediator** | [048](048-chat) |
| Salvar e restaurar o estado de um objeto sem quebrar o encapsulamento | **Memento** | [049](049-vida) |
| O comportamento muda conforme o estado, e os `if`/`switch` sobre o estado se espalham | **State** | [050](050-vida-de-gato) |
| Trocar o algoritmo (regra de cálculo, comportamento) sem mudar quem o usa | **Strategy** | [051](051-caes-polimorficos), [052](052-descontos) |
| Um processo tem passos fixos e alguns passos variam entre subclasses | **Template Method** | [053](053-cha-ou-cafe) |
| Acrescentar operações a uma estrutura de objetos sem alterar suas classes | **Visitor** | [054](054-carrinho-de-compras) |

[← Voltar ao sumário do curso](../README.md#sumário)
