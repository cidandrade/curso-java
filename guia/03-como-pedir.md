# 3. Como pedir

Um pedido de projeto orientado a objetos funciona como uma especificação curta. Quanto mais decisões você deixa em aberto, mais a IA decide sozinha, e ela costuma escolher o caminho mais curto: uma classe grande, com `if` para tudo.

## Anatomia de um bom pedido

| Parte | O que dizer | Exemplo |
|---|---|---|
| **Contexto** | Que sistema é e para quem | "Sistema de pedidos de uma cafeteria pequena" |
| **Entidades e responsabilidades** | As classes que você já identificou e o que cada uma faz (veja o [capítulo 1](01-pensar-em-objetos.md)) | "`Pedido` guarda os itens e calcula o total; `Item` tem produto e quantidade" |
| **O que varia** | Os pontos de extensão previstos | "Novas formas de pagamento vão surgir" |
| **Desenho desejado** | O padrão ou princípio, **com o motivo** | "Use Strategy para o pagamento, para incluir formas novas sem alterar `Pedido`" |
| **Restrições** | Versão do Java, bibliotecas permitidas e proibidas, estilo | "Java 17, sem frameworks, sem Lombok, atributos privados" |
| **Fora do escopo** | O que não deve ser feito agora | "Sem interface gráfica e sem banco de dados por enquanto" |
| **Como verificar** | Testes ou exemplos de entrada e saída ([063](../10-projetando-com-ia/063-testes-com-junit)) | "Inclua testes JUnit 5: pedido vazio custa zero; dois cafés de R$ 5 custam R$ 10" |
| **Formato da resposta** | Como você quer receber o resultado | "Um arquivo por classe, e explique em três linhas cada decisão de design" |

Não é preciso usar todas as partes em todo pedido. Mas, se você não sabe preencher uma delas, provavelmente ainda não pensou o projeto o suficiente.

## Ruim × bom

**Pedido vago:**

> Faça um sistema de pedidos para uma cafeteria em Java.

O que costuma vir: uma classe `Cafeteria` com `main`, `ArrayList` de `String`, preços fixos no código, pagamento como `if (tipo.equals("pix"))` e nenhum teste.

**Pedido que descreve o projeto:**

> Estou projetando o sistema de pedidos de uma cafeteria, em Java 17, sem frameworks.
>
> Classes: `Produto` (nome e preço, imutável), `Item` (produto e quantidade), `Pedido` (lista de itens; calcula o total; situação ABERTO, PAGO ou CANCELADO, num `enum`).
>
> O pagamento varia: hoje é cartão e Pix, mas vão surgir outros. Use o padrão Strategy: uma interface `FormaPagamento` com `boolean pagar(BigDecimal valor)`, uma classe por forma, e `Pedido.pagar(FormaPagamento)`. `Pedido` não pode conhecer as classes concretas.
>
> Use `BigDecimal` para dinheiro e mantenha os atributos privados, sem setters para a lista de itens.
>
> Inclua testes JUnit 5 para: total de um pedido vazio; total com dois itens; pagar muda a situação para PAGO; não é possível pagar um pedido cancelado.
>
> Sem interface gráfica nem banco. Depois do código, liste em tópicos as decisões de design que você tomou.

## Trabalhe em etapas

- **Peça primeiro o desenho, depois o código.** "Proponha as classes e um diagrama Mermaid; não escreva código ainda." Corrigir o desenho é muito mais barato que corrigir o código.
- **Uma responsabilidade por pedido.** Primeiro o domínio, depois a persistência, depois a interface. A cada etapa, revise ([capítulo 4](04-como-revisar.md)).
- **Peça a justificativa.** "Por que você usou herança aqui?" A resposta mostra se a decisão foi pensada ou foi só o caminho mais curto.
- **Dê o código existente como contexto.** Ao estender um projeto, mostre as interfaces que já existem e diga "siga o mesmo padrão de `X`".

## Cuidados

- Nomear um padrão sem o problema pode gerar um padrão onde ele não faz sentido. Diga sempre **por que** você quer o padrão.
- A IA pode afirmar que aplicou um padrão sem tê-lo aplicado. Confira os papéis no código (veja a tabela de papéis no [capítulo 2](02-vocabulario-de-design.md#os-papéis-dos-padrões)).
- Peça a versão do Java explicitamente. Sem isso, a IA pode misturar recursos de versões diferentes (veja [067 — Java moderno](../10-projetando-com-ia/067-java-moderno)).

---

← [2. Vocabulário de design](02-vocabulario-de-design.md) · [Guia](README.md) · [4. Como revisar](04-como-revisar.md) →
