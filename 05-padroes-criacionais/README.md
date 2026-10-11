# Padrões de projeto criacionais

Problemas de **criação de objetos**: quem decide qual classe instanciar, como montar um objeto complexo passo a passo, como copiar um modelo pronto e como garantir uma única instância. Saber o nome do padrão é o atalho para pedir à IA exatamente esse desenho.

| Nº | Aula | Tema |
|---|---|---|
| 026 | [Estúdio](026-estudio) | Padrão Factory Method (criacional) |
| 027 | [Estúdio melhorado](027-estudio-melhorado) | Padrão Abstract Factory (criacional), combinado com Factory Method |
| 028 | [Hamburgueria](028-hamburgueria) | Padrão Builder (criacional) |
| 029 | [Nova Hamburgueria](029-nova-hamburgueria) | Padrão Prototype (criacional) |
| 030 | [Jogo de Cartas](030-jogo-de-cartas) | Padrão Singleton (criacional) |

## Se você precisa…, peça um…

Use esta tabela para reconhecer o problema e saber que nome dar a ele num pedido à IA. O mapa de todos os padrões está no [guia](../guia/05-mapa-problema-padrao.md).

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| A classe que cria não deve depender da classe concreta criada; cada subclasse decide o que instanciar | **Factory Method** | [026](026-estudio) |
| Criar famílias de objetos que precisam combinar entre si | **Abstract Factory** | [027](027-estudio-melhorado) |
| Montar um objeto com muitas partes opcionais, passo a passo | **Builder** | [028](028-hamburgueria) |
| Criar objetos copiando um modelo já configurado | **Prototype** | [029](029-nova-hamburgueria) |
| Garantir uma única instância, acessível de qualquer ponto | **Singleton** | [030](030-jogo-de-cartas) |

[← Voltar ao sumário do curso](../README.md#sumário)
