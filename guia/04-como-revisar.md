# 4. Como revisar o código gerado

Código que compila e roda não é, só por isso, código bem projetado. A IA acerta a sintaxe quase sempre; os problemas aparecem no desenho, e é aí que a revisão tem de olhar.

## Primeiro: faz o que foi pedido?

- [ ] Rode o código. Se houver testes, rode os testes; se não houver, peça-os (veja [063 — Testes com JUnit](../10-projetando-com-ia/063-testes-com-junit)).
- [ ] Confira se **todas** as regras do pedido foram atendidas. A IA às vezes simplifica uma regra sem avisar.
- [ ] Teste os casos de borda: lista vazia, valor zero ou negativo, `null`, texto em branco.
- [ ] Desconfie de qualquer coisa que você não pediu: bibliotecas extras, classes "auxiliares", funcionalidades a mais.

## Depois: o desenho está bom?

| Verifique | Sinal de problema |
|---|---|
| **Responsabilidade única** | Classe com centenas de linhas ou com nome vago (`Gerenciador`, `Sistema`, `Utils`) |
| **Encapsulamento** | Getters e setters para todos os atributos; listas internas devolvidas sem proteção (`return itens;`) |
| **Objetos com comportamento** | Classes que só guardam dados, enquanto a lógica que as usa fica numa classe "serviço" gigante (modelo anêmico) |
| **Ponto de variação** | `if`/`switch` sobre tipo ou situação repetido em vários métodos, onde caberia polimorfismo ([State](../07-padroes-comportamentais/050-vida-de-gato), [Strategy](../07-padroes-comportamentais/051-caes-polimorficos)) |
| **Herança bem usada** | Subclasse que sobrescreve um método para não fazer nada ou para lançar exceção (fere Liskov) |
| **Dependências** | `new` de classes concretas de infraestrutura dentro da regra de negócio; impossível testar sem banco ou rede |
| **Camadas** | `JOptionPane`, `System.out` ou SQL dentro das classes de domínio ([MVC](../08-arquitetura-e-persistencia/055-mvc)) |
| **Padrão no lugar certo** | Padrão aplicado sem o problema que o justifica, ou só o nome do padrão, sem os papéis (veja os [papéis](02-vocabulario-de-design.md#os-papéis-dos-padrões)) |
| **Tipos adequados** | `double` para dinheiro (use `BigDecimal`, [016](../02-recursos-da-linguagem/016-matematica)); `String` para valores fechados (use `enum`, [017](../02-recursos-da-linguagem/017-enumeracao)) |
| **Exceções** | `catch` vazio, `catch (Exception e)` genérico, `printStackTrace()` como tratamento |

## Erros típicos da IA

- **Inventar API.** Métodos ou bibliotecas que não existem ou que pertencem a outra versão do Java. O compilador pega a maioria; leia os erros antes de pedir "conserte".
- **Detalhe que parece certo.** Uma constante errada numa fórmula, um parâmetro de setter ignorado, um `run()` chamado no lugar de `start()` numa thread. O código roda, mas está errado. Só um teste ou uma leitura atenta pegam esse tipo de erro.
- **Concorrência ingênua.** Estado compartilhado entre threads sem sincronização, ou variáveis atômicas reatribuídas em vez de atualizadas.
- **Recursos não fechados.** Arquivos, conexões e sockets abertos sem try-with-resources ([007](../01-fundamentos/007-entrada-e-saida)).
- **Excesso de padrões.** Fábrica de fábricas, interface com uma única implementação "para o futuro", camadas que só repassam chamadas.
- **Concordar demais.** Quando você aponta um problema, a IA concorda e "corrige", às vezes piorando o resto. Revise de novo depois de cada correção.

## Como pedir a correção

Seja específico e explique o princípio, não só o sintoma:

> Em `Pedido.pagar`, há um `switch` sobre o tipo de pagamento. Isso obriga a alterar `Pedido` a cada forma nova (fere o princípio aberto/fechado). Troque por uma interface `FormaPagamento`, com uma classe por forma, e mantenha os testes existentes passando.

---

← [3. Como pedir](03-como-pedir.md) · [Guia](README.md) · [5. Mapa problema → padrão](05-mapa-problema-padrao.md) →
