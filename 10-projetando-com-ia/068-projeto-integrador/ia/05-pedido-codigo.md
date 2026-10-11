# Rodada 3 — Pedido: decisões sobre as lacunas e o código

Enviado na mesma conversa, depois da revisão dos testes.

---

Testes aprovados. Respostas aos pontos em aberto:

- reservar numa sala que não existe: `RegraVioladaException`;
- cancelar uma reserva que não existe: `RegraVioladaException`;
- `Matricula` nula ou em branco: `RegraVioladaException`;
- cadastrar duas salas com o mesmo número: `RegraVioladaException`;
- buscar salas num horário que já começou: devolve lista vazia.

Escreva os testes desses cinco pontos em **arquivos novos** (`MatriculaTest`, `CatalogoDeSalasTest` e `CentralDeReservasLacunasTest`), sem mexer nos testes que já aprovei.

Agora escreva **todo o código de produção** para que todos os testes passem, sem alterar nenhum teste:

- Java 17, sem frameworks e sem bibliotecas além do JUnit;
- mantenha os pacotes que os testes já fixaram (`reservas.dominio`, `reservas.infra`, `reservas.aplicacao`);
- Javadoc curto em cada classe pública dizendo a responsabilidade dela;
- inclua uma classe `reservas.ReservasApp` com `main`, que seja a composition root: monta catálogo, repositório e central com `Clock.systemDefaultZone()` e mostra um cenário curto no console (reservar para amanhã, uma tentativa recusada pela cota e uma busca). O cenário precisa funcionar em qualquer hora do dia em que for executado.

Mande cada arquivo completo, com o caminho.
