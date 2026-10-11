# Modelagem: do enunciado às classes

Este é o raciocínio que levou ao código desta aula. Ele segue o método do [guia, capítulo 1](../../guia/01-pensar-em-objetos.md) e é o que você deve fazer **antes** de pedir o código a uma IA.

## 1. O enunciado

> A biblioteca do bairro quer controlar seus empréstimos. Ela tem vários livros, e de alguns títulos há mais de um exemplar. Cada sócio pode pegar até 3 livros ao mesmo tempo, por 14 dias. Quem devolve com atraso paga multa de R$ 1,00 por dia, e quem está com algum livro atrasado não pode pegar outro até devolvê-lo.

## 2. Primeira leitura: substantivos

biblioteca, empréstimo, livro, título, exemplar, sócio, dia (prazo), atraso, multa.

## 3. Segunda leitura: verbos

controlar empréstimos, pegar (emprestar), devolver, pagar multa.

## 4. Cortes e decisões

| Candidato | Decisão | Por quê |
|---|---|---|
| **livro** × **exemplar** | **Duas classes:** `Livro` e `Exemplar` | O enunciado diz "de alguns títulos há mais de um exemplar". O que tem título e autor é o livro, mas o que se empresta e fica indisponível é o exemplar. Juntar os dois numa classe só obriga a duplicar título e autor, ou a guardar uma "quantidade" que não diz *qual* volume está com *quem*. É o erro de modelagem mais comum neste caso, e uma IA o comete se você pedir "cadastro de livros com empréstimo". |
| título | Atributo de `Livro` | É só um dado, sem comportamento |
| **empréstimo** | **Classe** `Emprestimo` | Liga um sócio a um exemplar por um período e tem regras próprias: prazo, atraso, multa e devolução |
| **sócio** | **Classe** `Socio` | Tem estado (os empréstimos ativos) e regras (limite e bloqueio por atraso) |
| biblioteca | Vira `Acervo` | "Biblioteca" é o sistema inteiro. Uma classe com esse nome tende a virar o lugar de tudo. O que sobra de responsabilidade real é responder pelo conjunto de exemplares: "quais exemplares deste livro estão disponíveis?" |
| dia, prazo, atraso | Calculados por `Emprestimo` | Derivam da data do empréstimo; não precisam ser guardados |
| multa | Método `Emprestimo.multa(data)` | É um cálculo, não um objeto com vida própria. Se a multa precisasse ser paga e quitada depois, viraria classe |
| pagar multa | Fora do escopo | O enunciado não diz como se paga; `devolver` só informa o valor |

## 5. Onde fica cada regra?

A pergunta decisiva é: **quem tem a informação para aplicar a regra?**

| Regra | Quem sabe | Fica em |
|---|---|---|
| Prazo de 14 dias | O empréstimo conhece a própria data | `Emprestimo.getDataPrevista()` |
| R$ 1,00 por dia de atraso | O empréstimo conhece o prazo | `Emprestimo.multa(data)` |
| Exemplar emprestado não sai de novo | O exemplar sabe se está emprestado | `Exemplar.emprestar(...)` |
| Até 3 empréstimos; nenhum atrasado | O sócio conhece os próprios empréstimos | `Socio.podeEmprestar(hoje)` |

Repare que nenhuma regra ficou numa classe `BibliotecaService`. Esta é a versão que uma IA costuma entregar quando não recebe a modelagem:

```java
// Modelo anêmico: as classes só guardam dados...
class Livro { String titulo; int quantidade; boolean emprestado; /* getters e setters */ }
class Socio { String nome; int qtdEmprestimos; /* getters e setters */ }

// ...e todas as regras se acumulam num "serviço"
class BibliotecaService {
    void emprestar(Socio s, Livro l) {
        if (s.getQtdEmprestimos() >= 3) throw ...;
        if (l.getQuantidade() == 0) throw ...;
        l.setQuantidade(l.getQuantidade() - 1);
        s.setQtdEmprestimos(s.getQtdEmprestimos() + 1);
        // e quem pegou qual exemplar? e quando vence?
    }
}
```

Os setters públicos deixam qualquer código colocar o sócio com −1 empréstimos, e a `quantidade` não responde quem está com qual volume nem quando ele vence.

## 6. Cartões CRC

| Classe | Responsabilidades | Colaboradores |
|---|---|---|
| `Livro` | identificar a obra (o ISBN define a igualdade) | — |
| `Exemplar` | saber se está disponível; recusar um segundo empréstimo | `Livro`, `Emprestimo` |
| `Socio` | saber se pode pegar livros; pegar um exemplar | `Emprestimo`, `Exemplar` |
| `Emprestimo` | calcular prazo, atraso e multa; registrar a devolução | `Socio`, `Exemplar` |
| `Acervo` | guardar os exemplares; listar os disponíveis de um livro | `Exemplar`, `Livro` |

## 7. Duas decisões de implementação

- **A data de hoje é um parâmetro.** Os métodos recebem `LocalDate hoje` em vez de chamar `LocalDate.now()` lá dentro. Assim, o teste "devolvido 3 dias depois do prazo" não precisa esperar 17 dias. A aula 066 mostra outra forma de fazer isso, injetando um relógio.
- **Só o próprio pacote muda o estado.** `Exemplar.emprestar` e `Socio.encerrar` não são públicos: o único caminho para emprestar é `socio.emprestar(...)`, que confere as regras antes.

## 8. O pedido que sai desta modelagem

Com a análise pronta, o pedido à IA praticamente se escreve sozinho. Ele está no [README da aula](README.md#como-pedir-à-ia).
