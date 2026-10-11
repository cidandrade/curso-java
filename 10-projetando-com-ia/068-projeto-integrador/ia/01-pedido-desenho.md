# Rodada 1 — Pedido: o desenho

Enviado a um assistente de IA sem nenhum contexto sobre o curso, numa conversa nova.

---

Estou projetando um sistema em Java 17, sem frameworks. Enunciado:

> A faculdade tem salas de estudo que os alunos reservam. Cada sala tem um número, uma capacidade e pode ter projetor e/ou lousa digital. As salas funcionam das 8h às 22h, e as reservas são em horas cheias, de 1 a 2 horas seguidas. Uma sala não pode ter duas reservas no mesmo horário. Cada aluno pode reservar no máximo 2 horas por dia, somando todas as reservas dele. O tamanho do grupo informado na reserva não pode passar da capacidade da sala. O aluno pode cancelar até 1 hora antes do início; depois disso, não. Não se pode reservar no passado. O aluno precisa poder buscar as salas livres num horário, com uma capacidade mínima e, opcionalmente, exigindo projetor.

Antes de qualquer código, proponha:

1. as classes, com as responsabilidades e os colaboradores de cada uma (como cartões CRC);
2. um diagrama de classes em Mermaid;
3. uma tabela dizendo em que classe fica cada regra do enunciado.

Quero um modelo rico: cada regra fica na classe que tem a informação para aplicá-la, sem uma classe "Service" concentrando toda a lógica e sem setters públicos. A data e a hora atuais devem vir de um `java.time.Clock` injetado, e o armazenamento das reservas deve ficar atrás de uma interface, com uma implementação em memória. Use `record` onde fizer sentido para objetos de valor.

Não escreva o código ainda.
