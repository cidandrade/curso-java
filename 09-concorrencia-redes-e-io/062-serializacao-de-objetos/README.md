# 062 — Serialização de Objetos

**Tema:** Serialização

## Objetivo

Gravar um objeto em arquivo e lê-lo de volta com a serialização do Java, mostrando o efeito de `transient`.

## Conceitos-chave

- Interface `Serializable`
- `ObjectOutputStream.writeObject` e `ObjectInputStream.readObject`
- Serialização de objetos compostos (`Mago` tem um `Endereco`) e de enums
- `transient`: o campo `observacao` não é gravado e volta `null`

## Classes principais

| Classe | Papel |
|---|---|
| `Mago` | Objeto serializado; `observacao` é `transient` |
| `Endereco` | Objeto serializável dentro do `Mago` |
| `Casa` | Enum das casas |
| `ManipulaObjeto` | Salva e recupera objetos em `/tmp/` |
| `SalvaApp` | Aplicação: mostra o objeto antes e depois da serialização |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 09-concorrencia-redes-e-io/062-serializacao-de-objetos exec:java
```

Classe principal: `br.com.cidandrade.aulas.SalvaApp`. Na IDE, basta executar essa classe.

A saída aparece em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico. O arquivo é gravado em `/tmp/harry_file`, caminho que existe no Linux e no macOS; no Windows, altere-o para testar.

## Navegação

← [061 — Cliente TCP](../../09-concorrencia-redes-e-io/061-cliente-tcp) · [Sumário do curso](../../README.md#sumário) · [E001 — Números primos](../../exercicios/E001-numeros-primos) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-062---Serializa--o-de-Objetos`](https://github.com/cidandrade/Codigo-062---Serializa--o-de-Objetos), hoje arquivado.
