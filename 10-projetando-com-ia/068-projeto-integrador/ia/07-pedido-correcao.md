# Rodada 4 — Pedido: correções depois da revisão

Enviado na mesma conversa, depois de compilar, rodar os testes, rodar o app e aplicar testes de mutação ao código.

---

Rodei tudo: os 144 testes passaram de primeira e o app gera exatamente a saída que você previu. Depois, quebrei o código de propósito em 11 pontos (testes de mutação) para ver se os testes percebem. Sete mutações foram pegas, mas quatro passaram sem nenhum teste falhar:

1. **Tirei o `repositorio.salvar(reserva)` do `CentralDeReservas.cancelar` e nenhum teste falhou.** O repositório em memória devolve o próprio objeto, então o cancelamento "persiste" mesmo sem salvar. Com um banco de verdade, o cancelamento se perderia. Os testes precisam pegar esse erro. Não quero mudar os testes já aprovados nem a API pública: resolva com um teste novo (por exemplo, com um repositório espião que registra as chamadas a `salvar`) e diga se o `RepositorioDeReservasEmMemoria` também deveria mudar.
2. **Desliguei a checagem do construtor da `AgendaDaSala` (reserva de outra sala ou de outro dia) e nenhum teste falhou.** Você apresentou essa checagem como uma vantagem, mas ela não está coberta.
3. **Desliguei a checagem do `CotaDiariaDoAluno.registrar` (reserva de outro aluno ou de outro dia) e nenhum teste falhou.** Mesmo caso.
4. **Tirei o filtro `estaAtiva` de `ativasDoAluno` no repositório em memória e nenhum teste falhou.** O contrato do repositório ("só ativas") precisa de teste próprio.

E dois ajustes de código:

5. `ReservaId.toString()` corta o UUID em 8 caracteres. Isso torna ambíguas as mensagens de erro e os registros. Use o UUID completo.
6. `CatalogoDeSalas.todas()` não foi pedido e não é usado. Remova.

Escreva os testes novos em arquivos novos, sem alterar os testes já aprovados, e mande completos só os arquivos que mudaram ou foram criados.
