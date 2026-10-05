# 038 — Novo Carro

**Tema:** Padrão Facade (estrutural)

## Objetivo

Ligar e desligar o motor de um carro com uma única chamada, escondendo a sequência de operações dos vários componentes.

## Conceitos-chave

- Facade: uma classe coordena vários objetos do subsistema
- Ordem das operações encapsulada na fachada
- Baixo acoplamento entre o cliente e o subsistema

## Classes principais

| Classe | Papel |
|---|---|
| `CarroFacade` | **Fachada**: `ligarMotor()` e `desligarMotor()` |
| `InjetorDeCombustivel`, `ControladorFluxoDeAr`, `Ignicao`, `Radiador`, `Catalisador` | **Subsistema** |
| `CarroApp` | **Cliente** |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/038-novo-carro exec:java
```

Classe principal: `br.com.cidandrade.aulas.CarroApp`. Na IDE, basta executar essa classe.

A saída é no console.

## Navegação

← [037 — Novo Formas Coloridas](../../06-padroes-estruturais/037-novo-formas-coloridas) · [Sumário do curso](../../README.md#sumário) · [039 — Cafeteria Leve](../../06-padroes-estruturais/039-cafeteria-leve) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-038---Novo-Carro`](https://github.com/cidandrade/Codigo-038---Novo-Carro), hoje arquivado.
