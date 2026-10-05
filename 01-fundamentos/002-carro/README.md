# 002 — Carro

**Tema:** Classes e objetos

## Objetivo

Criar a primeira classe com estado (atributos) e comportamento (métodos) e instanciar dois objetos independentes dela.

## Conceitos-chave

- Classe × objeto
- Atributos privados e encapsulamento
- Métodos que alteram o estado do objeto
- Operador `new`
- Organização em pacotes (`classes`)

## Classes principais

| Classe | Papel |
|---|---|
| `Carro` | Guarda velocidade e marcha; oferece `mudarMarcha`, `aumentarVelocidade`, `frear` e `imprimirEstadoDoCarro` |
| `CarroApp` | Aplicação: cria dois carros e mostra que cada um tem seu próprio estado |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 01-fundamentos/002-carro exec:java
```

Classe principal: `br.com.cidandrade.aulas.CarroApp`. Na IDE, basta executar essa classe.

## Navegação

← [001 — Olá, Mundo](../../01-fundamentos/001-ola-mundo) · [Sumário do curso](../../README.md#sumário) · [003 — Herança](../../01-fundamentos/003-heranca) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-002---Carro`](https://github.com/cidandrade/Codigo-002---Carro), hoje arquivado.
