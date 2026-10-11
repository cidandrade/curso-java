# 5. Mapa problema → padrão

Antes de pedir um padrão à IA, descreva o problema. Esta tabela faz o caminho inverso: parte do problema que você reconhece no seu projeto e chega ao nome do padrão, que é o termo que leva a IA ao desenho certo.

Dois cuidados:

- **Nem todo problema pede um padrão.** Se a solução simples resolve, peça a solução simples. Um padrão aplicado sem o problema que ele resolve só deixa o código mais difícil (veja [Como revisar](04-como-revisar.md)).
- **Diga o problema junto com o nome.** "Use Strategy" sozinho pode gerar um Strategy no lugar errado. "As regras de desconto mudam com frequência; use Strategy para que cada regra seja uma classe" diz onde e por quê.

## Padrões de projeto criacionais

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| A classe que cria não deve depender da classe concreta criada; cada subclasse decide o que instanciar | **Factory Method** | [026](../05-padroes-criacionais/026-estudio) |
| Criar famílias de objetos que precisam combinar entre si | **Abstract Factory** | [027](../05-padroes-criacionais/027-estudio-melhorado) |
| Montar um objeto com muitas partes opcionais, passo a passo | **Builder** | [028](../05-padroes-criacionais/028-hamburgueria) |
| Criar objetos copiando um modelo já configurado | **Prototype** | [029](../05-padroes-criacionais/029-nova-hamburgueria) |
| Garantir uma única instância, acessível de qualquer ponto | **Singleton** | [030](../05-padroes-criacionais/030-jogo-de-cartas) |

## Padrões de projeto estruturais

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| Usar uma classe pronta cuja interface não é a que o sistema espera | **Adapter** | [031](../06-padroes-estruturais/031-quem-nao-tem-cao) |
| Duas dimensões variam de forma independente (ex.: forma × cor) e a herança explodiria em subclasses | **Bridge** | [032](../06-padroes-estruturais/032-formas-coloridas), [033](../06-padroes-estruturais/033-questionario) |
| Tratar um item e um grupo de itens da mesma forma, numa árvore | **Composite** | [034](../06-padroes-estruturais/034-empacotamento) |
| Acrescentar responsabilidades a um objeto em tempo de execução, em camadas | **Decorator** | [035](../06-padroes-estruturais/035-cafeteria), [036](../06-padroes-estruturais/036-arvore-de-natal) |
| Oferecer uma porta de entrada simples para um subsistema complicado | **Facade** | [037](../06-padroes-estruturais/037-novo-formas-coloridas), [038](../06-padroes-estruturais/038-novo-carro) |
| Muitos objetos parecidos gastam memória demais; compartilhar a parte comum | **Flyweight** | [039](../06-padroes-estruturais/039-cafeteria-leve) |
| Controlar o acesso a um objeto (permissão, carga tardia, registro) sem mudar quem o usa | **Proxy** | [040](../06-padroes-estruturais/040-magos-e-torres) |

## Padrões de projeto comportamentais

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| Um pedido passa por vários tratadores até que um deles resolva | **Chain of Responsibility** | [041](../07-padroes-comportamentais/041-credito) |
| Vários objetos precisam saber quando outro muda | **Observer** | [042](../07-padroes-comportamentais/042-meteorologia), [043](../07-padroes-comportamentais/043-novo-meteorologia), [044](../07-padroes-comportamentais/044-meteorologia-gui) |
| Guardar ações como objetos para executar, enfileirar, registrar ou desfazer | **Command** | [045](../07-padroes-comportamentais/045-lampada), [046](../07-padroes-comportamentais/046-comandos-em-arquivo) |
| Percorrer coleções diferentes do mesmo jeito, sem expor como são guardadas | **Iterator** | [047](../07-padroes-comportamentais/047-menus-diferentes) |
| Muitos objetos conversam entre si e o acoplamento virou uma teia | **Mediator** | [048](../07-padroes-comportamentais/048-chat) |
| Salvar e restaurar o estado de um objeto sem quebrar o encapsulamento | **Memento** | [049](../07-padroes-comportamentais/049-vida) |
| O comportamento muda conforme o estado, e os `if`/`switch` sobre o estado se espalham | **State** | [050](../07-padroes-comportamentais/050-vida-de-gato) |
| Trocar o algoritmo (regra de cálculo, comportamento) sem mudar quem o usa | **Strategy** | [051](../07-padroes-comportamentais/051-caes-polimorficos), [052](../07-padroes-comportamentais/052-descontos) |
| Um processo tem passos fixos e alguns passos variam entre subclasses | **Template Method** | [053](../07-padroes-comportamentais/053-cha-ou-cafe) |
| Acrescentar operações a uma estrutura de objetos sem alterar suas classes | **Visitor** | [054](../07-padroes-comportamentais/054-carrinho-de-compras) |

---

← [4. Como revisar o código gerado](04-como-revisar.md) · [Guia](README.md) · [Sumário do curso](../README.md#sumário)
