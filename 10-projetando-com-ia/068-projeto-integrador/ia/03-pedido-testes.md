# Rodada 2 — Pedido: revisão do desenho e os testes

Enviado na mesma conversa, depois da revisão do desenho.

---

Revisei o desenho. Está bom no geral, mas quero dois ajustes antes de seguir:

1. **A cota de 2 horas pode ser pulada.** O construtor package-private da `Reserva` garante que ninguém cria uma reserva sem passar pela checagem de conflito, mas a checagem da cota depende de a `CentralDeReservas` lembrar de chamar `garantirQueCabe` antes. Isso contradiz o seu próprio critério ("apagar a Central não remove nenhuma regra"). Faça a `AgendaDaSala.reservar` exigir a `CotaDiariaDoAluno` como parâmetro e chamar `garantirQueCabe` ela mesma, para que seja impossível criar uma reserva sem as duas checagens.
2. **`Sala.atende(CriterioDeBusca)` inverte a dependência.** A entidade `Sala` não deveria conhecer o objeto de consulta da busca. Troque por `CriterioDeBusca.aceita(Sala)`, usando o que a sala já expõe (`comporta` e os recursos).

Sobre as premissas: aceito as 1, 2, 4, 5, 6 (sem `synchronized`) e 7 (fora do escopo). A 3 ("só o dono cancela") não estava no enunciado, mas faz sentido: pode manter, e eu vou registrá-la como regra acrescentada.

Agora, **ainda sem o código de produção**, escreva os testes JUnit 5 (Jupiter) que especificam o sistema, em Java 17:

- um teste por regra, com `@DisplayName` em português descrevendo a regra;
- casos de borda: reserva às 8h e às 21h (com 1 hora), 21h com 2 horas (deve falhar), duração 0 e 3, grupo igual à capacidade e uma pessoa a mais, cota de 1h + 1h (passa) e de 2h + 1h (falha), reserva cancelada que não conta para a cota nem para o conflito, cancelamento exatamente 1 hora antes (passa) e um segundo depois disso (falha);
- os testes da `CentralDeReservas` usam `Clock.fixed` e o repositório em memória;
- testes parametrizados onde a mesma regra tiver vários exemplos.

Depois que eu aprovar os testes, vou pedir o código para fazê-los passar, sem alterar nenhum teste.
