# 2. Vocabulário de design

A IA conhece todos estes termos e responde a eles com desenhos bem definidos. Usar a palavra certa num pedido vale mais do que um parágrafo de explicação. Também vale o contrário: se você não conhece o termo, não percebe quando a IA o aplica mal.

## Conceitos da orientação a objetos

| Termo | O que significa | Na prática, num pedido | Aula |
|---|---|---|---|
| **Encapsulamento** | O estado do objeto só muda pelos métodos dele | "atributos privados; nada de setter para o saldo, só `depositar` e `sacar`" | [002](../01-fundamentos/002-carro) |
| **Herança** | Uma subclasse *é um* tipo da superclasse e herda o comportamento dela | "`SUV` estende `Carro`" | [003](../01-fundamentos/003-heranca) |
| **Interface** | Um contrato: o que a classe faz, não como faz | "defina a interface `Pagamento` com `processar(valor)`" | [004](../01-fundamentos/004-interface) |
| **Classe abstrata** | Uma base com parte do código pronta e parte deixada para as subclasses | "classe abstrata `Bebida` com o método `preparar()` final" | [009](../02-recursos-da-linguagem/009-classes-pojo), [053](../07-padroes-comportamentais/053-cha-ou-cafe) |
| **Polimorfismo** | Objetos diferentes respondem à mesma mensagem cada um a seu modo | "o carrinho chama `calcular()` sem saber qual regra é" | [051](../07-padroes-comportamentais/051-caes-polimorficos) |
| **Composição** | Um objeto *tem* outro e delega trabalho a ele | "o `Cao` tem um `ComportLatido`, em vez de herdar o latido" | [051](../07-padroes-comportamentais/051-caes-polimorficos) |
| **Enumeração** | Um conjunto fechado de valores com nome, que também pode ter comportamento | "use um `enum Situacao` em vez de `String`" | [017](../02-recursos-da-linguagem/017-enumeracao) |
| **Generics** | Uma classe ou método que funciona para vários tipos com segurança | "`Repositorio<T>` genérico" | [023](../03-colecoes-e-apis/023-quadrado) |
| **Imutabilidade** | O objeto não muda depois de criado; para "mudar", cria-se outro | "`Endereco` imutável, com campos `final`" | — |

## Qualidades de um projeto

| Termo | O que significa | Sinal de problema |
|---|---|---|
| **Coesão** | Tudo numa classe serve a um mesmo propósito | Classe com nome vago (`Gerenciador`, `Utils`, `Sistema`) que faz de tudo |
| **Acoplamento** | Quanto uma classe depende dos detalhes de outra | Mudar uma classe obriga a mudar várias outras |
| **Programar para a interface** | Declarar variáveis e parâmetros com o tipo da interface, não da classe concreta | `ArrayList<Item> itens` em vez de `List<Item> itens`; `new MySQLDao()` no meio da regra de negócio |
| **Composição em vez de herança** | Preferir combinar objetos a criar hierarquias profundas | Subclasses que existem só para mudar um detalhe; hierarquias com quatro níveis ou mais |
| **Separação de camadas** | Interface com o usuário, regras e persistência em lugares diferentes | SQL ou `JOptionPane` dentro da classe de domínio ([055 — MVC](../08-arquitetura-e-persistencia/055-mvc), [056 — Livraria](../08-arquitetura-e-persistencia/056-livraria)) |

## Os princípios SOLID

A aula [064 — SOLID na prática](../10-projetando-com-ia/064-solid-na-pratica) mostra os cinco no mesmo código, antes e depois.

| Princípio | Em uma frase | Peça assim |
|---|---|---|
| **S** — Responsabilidade única | Cada classe tem um só motivo para mudar | "separe o cálculo do pedido da geração da nota fiscal" |
| **O** — Aberto/fechado | Acrescentar comportamento sem alterar o código que já funciona | "novas formas de pagamento devem entrar sem mexer em `Pedido`" |
| **L** — Substituição de Liskov | Uma subclasse deve funcionar em qualquer lugar onde a superclasse funciona | "`Quadrado` não deve herdar de `Retangulo` se isso quebrar `setLargura`" |
| **I** — Segregação de interfaces | Interfaces pequenas e específicas, em vez de uma grande | "separe `Imprimivel` de `Exportavel`" |
| **D** — Inversão de dependência | Regras de alto nível dependem de abstrações, não de detalhes | "`Pedido` recebe um `Pagamento` pelo construtor" |

## Os papéis dos padrões

Cada padrão de projeto dá nome a papéis. Nomear os papéis no pedido evita que a IA invente um desenho parecido, mas diferente.

| Padrão | Papéis | Aula |
|---|---|---|
| Factory Method | Criador, Produto | [026](../05-padroes-criacionais/026-estudio) |
| Abstract Factory | Fábrica abstrata, Fábricas concretas, Família de produtos | [027](../05-padroes-criacionais/027-estudio-melhorado) |
| Builder | Construtor, Produto, Diretor (opcional) | [028](../05-padroes-criacionais/028-hamburgueria) |
| Prototype | Protótipo, método de cópia | [029](../05-padroes-criacionais/029-nova-hamburgueria) |
| Singleton | Instância única, ponto de acesso | [030](../05-padroes-criacionais/030-jogo-de-cartas) |
| Adapter | Alvo, Adaptador, Adaptado | [031](../06-padroes-estruturais/031-quem-nao-tem-cao) |
| Bridge | Abstração, Implementador | [032](../06-padroes-estruturais/032-formas-coloridas) |
| Composite | Componente, Folha, Composto | [034](../06-padroes-estruturais/034-empacotamento) |
| Decorator | Componente, Decorador | [035](../06-padroes-estruturais/035-cafeteria) |
| Facade | Fachada, Subsistema | [038](../06-padroes-estruturais/038-novo-carro) |
| Flyweight | Peso-mosca, Fábrica, estado intrínseco × extrínseco | [039](../06-padroes-estruturais/039-cafeteria-leve) |
| Proxy | Sujeito, Proxy, Sujeito real | [040](../06-padroes-estruturais/040-magos-e-torres) |
| Chain of Responsibility | Tratador, Sucessor | [041](../07-padroes-comportamentais/041-credito) |
| Observer | Sujeito (publicador), Observadores (assinantes) | [042](../07-padroes-comportamentais/042-meteorologia) |
| Command | Comando, Invocador, Receptor | [045](../07-padroes-comportamentais/045-lampada) |
| Iterator | Iterador, Agregado | [047](../07-padroes-comportamentais/047-menus-diferentes) |
| Mediator | Mediador, Colegas | [048](../07-padroes-comportamentais/048-chat) |
| Memento | Originador, Memento, Zelador | [049](../07-padroes-comportamentais/049-vida) |
| State | Contexto, Estado, Estados concretos | [050](../07-padroes-comportamentais/050-vida-de-gato) |
| Strategy | Contexto, Estratégia, Estratégias concretas | [051](../07-padroes-comportamentais/051-caes-polimorficos) |
| Template Method | Classe abstrata com o método-modelo, Passos | [053](../07-padroes-comportamentais/053-cha-ou-cafe) |
| Visitor | Visitante, Elementos visitados | [054](../07-padroes-comportamentais/054-carrinho-de-compras) |
| MVC | Modelo, Visão, Controlador | [055](../08-arquitetura-e-persistencia/055-mvc) |
| DAO | Objeto de acesso a dados, Entidade | [056](../08-arquitetura-e-persistencia/056-livraria) |

---

← [1. Pensar em objetos](01-pensar-em-objetos.md) · [Guia](README.md) · [3. Como pedir](03-como-pedir.md) →
