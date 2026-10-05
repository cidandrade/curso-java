# 010 — POJO com Lombok

**Tema:** Lombok

## Objetivo

Refazer a aula 009 com Lombok, que gera por anotações o código repetitivo dos POJOs.

## Conceitos-chave

- `@Getter`, `@Setter`, `@EqualsAndHashCode`, `@NoArgsConstructor`, `@AllArgsConstructor`
- Processamento de anotações em tempo de compilação
- `var` (inferência de tipo de variável local, Java 10+)
- Comparação direta com o código escrito à mão na aula 009

## Classes principais

| Classe | Papel |
|---|---|
| `GeometriaInterfaces` | Contrato: `getArea` e `getDescricao` |
| `FormaGeometrica` | Classe abstrata base das formas |
| `Triangulo` | POJO gerado pelo Lombok |
| `Retangulo` | POJO gerado pelo Lombok |
| `PojoApp` | Aplicação idêntica à da aula 009 |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 02-recursos-da-linguagem/010-pojo-com-lombok exec:java
```

Classe principal: `br.com.cidandrade.aulas.PojoApp`. Na IDE, basta executar essa classe.

Os resultados aparecem em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico. Para o NetBeans ou outra IDE reconhecer o código gerado, o processamento de anotações do Lombok precisa estar ativo (o `pom.xml` raiz já o configura no Maven).

## Navegação

← [009 — Classes POJO](../../02-recursos-da-linguagem/009-classes-pojo) · [Sumário do curso](../../README.md#sumário) · [011 — String](../../02-recursos-da-linguagem/011-string) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-010---Pojo-com-Lombok`](https://github.com/cidandrade/Codigo-010---Pojo-com-Lombok), hoje arquivado.
