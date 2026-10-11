# Diário do projeto: reserva de salas com IA

Este é o registro de um ciclo real de projeto com IA, do enunciado ao código testado. Os pedidos foram enviados a um assistente de IA (um modelo Claude) numa conversa nova, sem nenhuma informação sobre este curso. As respostas estão em [`ia/`](ia), na íntegra e sem edição. Todo o código em `src/` foi extraído dessas respostas sem alteração. Nenhuma linha foi escrita à mão.

A revisão de cada rodada foi feita como o curso ensina: lendo o resultado com as listas de verificação do [guia](../../guia/04-como-revisar.md), compilando, rodando os testes e quebrando o código de propósito para ver se os testes percebem.

Outra IA, ou a mesma IA noutro dia, daria respostas diferentes. O que se repete é o método.

## O enunciado

> A faculdade tem salas de estudo que os alunos reservam. Cada sala tem um número, uma capacidade e pode ter projetor e/ou lousa digital. As salas funcionam das 8h às 22h, e as reservas são em horas cheias, de 1 a 2 horas seguidas. Uma sala não pode ter duas reservas no mesmo horário. Cada aluno pode reservar no máximo 2 horas por dia, somando todas as reservas dele. O tamanho do grupo informado na reserva não pode passar da capacidade da sala. O aluno pode cancelar até 1 hora antes do início; depois disso, não. Não se pode reservar no passado. O aluno precisa poder buscar as salas livres num horário, com uma capacidade mínima e, opcionalmente, exigindo projetor.

## O ciclo em números

| Rodada | Pedido | Resposta | O que a revisão encontrou | Resultado |
|---|---|---|---|---|
| 1. Desenho | [pedido](ia/01-pedido-desenho.md) | [resposta](ia/02-resposta-desenho.md) | 2 falhas de desenho e 1 regra acrescentada pela IA | Desenho aprovado com ajustes |
| 2. Testes | [pedido](ia/03-pedido-testes.md) | [resposta](ia/04-resposta-testes.md) | 5 lacunas apontadas pela própria IA, 1 teste fraco e 1 omissão minha (o pacote) | 8 arquivos de teste aprovados |
| 3. Código | [pedido](ia/05-pedido-codigo.md) | [resposta](ia/06-resposta-codigo.md) | 144 testes passaram de primeira, mas 4 de 11 mutações sobreviveram; 2 problemas de código | Pedido de correção |
| 4. Correção | [pedido](ia/07-pedido-correcao.md) | [resposta](ia/08-resposta-correcao.md) | 11 de 11 mutações mortas | **162 testes**, projeto aceito |

## Rodada 1: o desenho

**O que foi pedido.** Só o desenho, sem código: classes com cartões CRC, diagrama Mermaid e uma tabela regra → classe. O pedido já trazia as decisões que o curso ensina: modelo rico, sem "Service", sem setters, relógio injetado, repositório atrás de interface e records para valores.

**O que veio de bom.** Um desenho cuidadoso:
- duas classes para as regras que envolvem várias reservas (`AgendaDaSala` e `CotaDiariaDoAluno`);
- o relógio só na borda, com o domínio recebendo o `agora` como parâmetro;
- o construtor de `Reserva` package-private;
- as premissas declaradas para conferência.

O pedido detalhado fez diferença: compare com o modelo anêmico que a [aula 065](../065-modelagem-de-dominio/MODELAGEM.md#5-onde-fica-cada-regra) mostra como resultado típico de um pedido vago.

**O que a revisão encontrou.**

1. **A garantia era parcial.** O construtor package-private impedia criar uma reserva sem a checagem de conflito, mas não sem a da cota. Quem garantia a cota era a `CentralDeReservas`, que tinha de lembrar de chamá-la antes. Isso contradizia o critério proposto pela própria IA ("apagar a Central não remove nenhuma regra"). *Correção pedida:* a `AgendaDaSala.reservar` passa a exigir a cota como parâmetro.
2. **A dependência estava invertida.** Em `Sala.atende(CriterioDeBusca)`, a entidade passava a conhecer o objeto de consulta da tela de busca. *Correção pedida:* `CriterioDeBusca.aceita(Sala)`.
3. **A IA acrescentou uma regra.** "Só o dono cancela" não está no enunciado. É sensata e foi aceita, mas de forma consciente, e ficou marcada nos testes como "Regra acrescentada". Regras inventadas pela IA passam despercebidas quando ninguém lê as premissas.

## Rodada 2: os testes antes do código

**O que foi pedido.** Os dois ajustes e os testes JUnit, ainda sem código de produção, com uma lista explícita de casos de borda (veja a [aula 063](../063-testes-com-junit)).

**O que veio de bom.** Todos os casos de borda pedidos, testes parametrizados, `Clock.fixed` e uma tabela da API que os testes fixam. A IA também fez dois alertas sem que ninguém pedisse:
- `RegraVioladaException` não pode herdar de `IllegalArgumentException`; senão, alguns testes passariam por engano;
- cinco pontos que os testes deixavam em aberto (sala inexistente, reserva inexistente, matrícula vazia, sala duplicada e busca no passado).

**O que a revisão encontrou.**

1. **Uma omissão minha.** O pedido não dizia o pacote, e a IA escolheu `reservas`, fora da convenção do curso (`br.com.cidandrade.aulas`). Como os testes já fixavam o nome, ficou assim. *Lição:* convenções do projeto (pacote, nomes, idioma) também vão no pedido.
2. **As lacunas foram decididas por mim.** As cinco viraram regras, com testes novos em arquivos separados, para não tocar nos testes já aprovados.
3. **Um teste fraco.** "Reservas em horas cheias" quase não pode falhar, porque o tipo `int` da hora já garante isso. Foi mantido, mas é um exemplo de teste que confirma o óbvio.

## Rodada 3: o código

**O que foi pedido.** O código de produção para os testes passarem, sem alterar nenhum teste, com um `main` que funcionasse a qualquer hora do dia.

**O que aconteceu ao rodar.**
- `mvn test`: **144 testes, todos passando na primeira execução.**
- O app gerou exatamente a saída que a IA tinha previsto.
- Os testes não foram alterados (conferido arquivo por arquivo).
- O `pom.xml` proposto pela IA não foi usado: ele inventava um `groupId` (`br.edu.faculdade`) e fixava o JUnit 5.10. A aula usa o pom do curso.

**Testes passando não bastam.** Depois, apliquei 11 mutações ao código, quebrando uma regra de cada vez, para ver se os testes percebiam:

| Mutação | Rodada 3 | Rodada 4 |
|---|---|---|
| Cancelar exatamente 1 hora antes passa a ser proibido | pega | pega |
| Sobreposição de horários com fim inclusivo | pega | pega |
| A agenda deixa de registrar a reserva na cota | pega | pega |
| A reserva cancelada continua consumindo horas | pega | pega |
| Limite da cota com `>=` em vez de `>` | pega | pega |
| Qualquer aluno pode cancelar | pega | pega |
| O repositório devolve reservas de outro aluno | pega | pega |
| O repositório devolve reservas canceladas | **sobreviveu** | pega |
| A agenda aceita reserva de outra sala | **sobreviveu** | pega |
| A cota aceita reserva de outro aluno | **sobreviveu** | pega |
| Cancelar deixa de salvar no repositório | **sobreviveu** | pega |

A mais séria foi a última. O repositório em memória devolve o próprio objeto, então o cancelamento "persistia" mesmo sem o `salvar`. Com um banco de dados, o cancelamento se perderia, e nenhum dos 144 testes perceberia. Um repositório em memória é condescendente demais para servir de prova de persistência.

**A leitura do código achou mais dois pontos.**
- `ReservaId.toString()` cortava o UUID em 8 caracteres, o que deixava as mensagens de erro ambíguas.
- `CatalogoDeSalas.todas()` não tinha sido pedido e não era usado.

## Rodada 4: a correção

**O que foi pedido.** As quatro mutações sobreviventes, explicadas uma a uma, mais os dois ajustes de código. Também a pergunta: o repositório em memória deveria mudar?

**O que veio.**
- Um **repositório espião** nos testes, que anota o status da reserva no instante de cada `salvar`.
- Um **teste de contrato** do repositório (`ContratoDoRepositorioDeReservas`), que qualquer implementação futura, inclusive com banco, terá de cumprir.
- Testes para as checagens de consistência. A IA ainda achou sozinha outras duas checagens sem teste.

Sobre o repositório, a IA recomendou **não** mudá-lo. Para guardar cópias, seria preciso um construtor público de `Reserva`, o que quebraria a garantia de que toda reserva passa pela agenda. A recomendação foi aceita, com a semântica documentada no Javadoc. É uma troca consciente, e não um descuido.

**Resultado.** 162 testes passando, as 11 mutações pegas e todos os arquivos idênticos às respostas da IA.

A IA ainda listou o que continua sem teste: as checagens de nulo, a forma do cálculo de horário e os `toString`. Também sugeriu o [PIT](https://pitest.org/), que automatiza os testes de mutação. Isso fica como próximo passo.

## Lições

1. **O pedido detalhado é a maior alavanca.** Desenho primeiro, regras ligadas a classes, modelo rico, relógio injetado, testes antes do código: o resultado da rodada 1 já era bom porque o pedido já continha o projeto. Tudo isso vem das aulas 063 a 067 e do [guia](../../guia/03-como-pedir.md).
2. **Uma IA boa ainda erra no desenho, só que de forma sutil.** Uma garantia que vale para uma regra e não para a outra, ou uma dependência na direção errada. Só pega esses erros quem sabe o que procurar.
3. **Leia as premissas.** É ali que a IA acrescenta regras que ninguém pediu.
4. **Os pontos em aberto são decisões suas.** A IA apontou as lacunas, mas quem decide o comportamento é quem conhece o negócio.
5. **Testes passando não provam que os testes são bons.** Os 144 testes passaram de primeira e, ainda assim, deixavam passar um bug que perderia todos os cancelamentos num banco de verdade. Quebrar o código de propósito (teste de mutação) mostra o que os testes realmente verificam.
6. **Código defensivo sem teste é promessa, não garantia.** As checagens que a IA apresentou como vantagem não tinham teste.
7. **Registre tudo.** Pedidos, respostas e decisões em arquivo permitem repetir o processo, explicar as escolhas e mostrar de onde veio cada linha.
