# 039 — Cafeteria Leve

**Tema:** Padrão Flyweight (estrutural)

## Objetivo

Atender os mesmos dez pedidos de duas maneiras, sem e com Flyweight, e comparar quantos objetos café são criados.

## Conceitos-chave

- Flyweight: compartilhar objetos que têm o mesmo estado
- Estado intrínseco (o sabor, compartilhado) × estado extrínseco (a arte, passada a cada uso)
- Fábrica com cache em `HashMap`
- Métodos de fábrica estáticos (`Pedido.de`)

## Classes principais

| Classe | Papel |
|---|---|
| `Cafe`, `CafeFabrica`, `Pedido`, `Cafeteria` | Versão sem Flyweight: um objeto `Cafe` por pedido |
| `CafeFW` | **Flyweight**: guarda só o sabor; a arte chega em `aplicarArte` |
| `CafeFabricaFW` | **Fábrica de flyweights**: reaproveita o café de cada sabor |
| `PedidoFW`, `CafeteriaFW` | **Clientes** da fábrica |
| `SaborDeCafe`, `ArteNoCafe` | Enums de sabores e artes |
| `CafeApp` | Aplicação: compara 10 cafés (sem Flyweight) com 5 (com Flyweight) |

## Como executar

Na raiz do repositório:

```bash
mvn -q install -DskipTests      # uma única vez: compila tudo e instala a biblioteca da aula 008
mvn -q -pl 06-padroes-estruturais/039-cafeteria-leve exec:java
```

Classe principal: `br.com.cidandrade.aulas.CafeApp`. Na IDE, basta executar essa classe.

O passo a passo sai no console, e as contagens em caixas de diálogo (`JOptionPane`), por isso é preciso ambiente gráfico.

## Navegação

← [038 — Novo Carro](../../06-padroes-estruturais/038-novo-carro) · [Sumário do curso](../../README.md#sumário) · [040 — Magos e Torres](../../06-padroes-estruturais/040-magos-e-torres) →

## Origem

Este código veio do repositório [`cidandrade/Codigo-039---Cafeteria-Leve`](https://github.com/cidandrade/Codigo-039---Cafeteria-Leve), hoje arquivado.
