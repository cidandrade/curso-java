# 041 — Crédito

**Tema:** Padrão Chain of Responsibility (comportamental)

## Objetivo

Avaliar pedidos de crédito passando cada requisição por uma cadeia de verificações: Serasa, regras do tipo de crédito e salário. Qualquer elo pode negar.

## Conceitos-chave

- Chain of Responsibility: cada elo faz seu teste e repassa ao `proximo`
- Cadeia montada pelos construtores e escolha dinâmica do próximo elo (`ChecagemPorTipo`)
- Singleton em `ListaDePessoas` e `Serasa`
- Dados sorteados: cada execução dá um resultado diferente
- `@Data` do Lombok

## Classes principais

| Classe | Papel |
|---|---|
| `Responsabilidade` | **Handler** (interface): `requisicaoAutorizada()` |
| `CoRRequisicao` | Handler abstrato: guarda `proximo` e a `requisicao` |
| `ConcessaoCredito` | Primeiro elo; ponto de entrada (`avaliarConcessaoCredito`) |
| `ChecagemSerasa` | Elo: pontuação mínima no Serasa |
| `ChecagemPorTipo` | Elo: escolhe o próximo conforme o tipo de crédito |
| `ChecagemConsignado`, `ChecagemCarro`, `ChecagemImovel`, `ChecagemPessoal` | Elos: limites de cada tipo |
| `ChecagemSalario` | Último elo: valor × salário |
| `Requisicao`, `Pessoa`, `TipoDeCredito` | Dados da requisição |
| `ListaDePessoas`, `Serasa`, `SerasaAutorizacao` | Dados simulados (Singletons) |
| `CreditoApp` | **Cliente**: avalia 15 requisições sorteadas |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 07-padroes-comportamentais/041-credito exec:java
```

Classe principal: `br.com.cidandrade.aulas.CreditoApp`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [040 — Magos e Torres](../../06-padroes-estruturais/040-magos-e-torres) · [Sumário do curso](../../README.md#sumário) · [042 — Meteorologia](../../07-padroes-comportamentais/042-meteorologia) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-041---Credito`](https://github.com/cidandrade/Codigo-041---Credito), hoje arquivado.
