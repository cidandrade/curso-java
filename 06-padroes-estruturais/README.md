# Padrões de projeto estruturais

Problemas de **composição**: encaixar uma classe que não segue a interface esperada, separar duas dimensões que variam, tratar parte e todo da mesma forma, acrescentar responsabilidades sem herança, simplificar um subsistema, compartilhar objetos e controlar o acesso a eles.

| Nº | Aula | Tema |
|---|---|---|
| 031 | [Quem não tem cão](031-quem-nao-tem-cao) | Padrão Adapter (estrutural) |
| 032 | [Formas Coloridas](032-formas-coloridas) | Padrão Bridge (estrutural) |
| 033 | [Questionário](033-questionario) | Padrão Bridge (estrutural) |
| 034 | [Empacotamento](034-empacotamento) | Padrão Composite (estrutural) |
| 035 | [Cafeteria](035-cafeteria) | Padrão Decorator (estrutural) |
| 036 | [Árvore de Natal](036-arvore-de-natal) | Padrão Decorator (estrutural) |
| 037 | [Novo Formas Coloridas](037-novo-formas-coloridas) | Padrão Facade (estrutural), sobre o Bridge da aula 032 |
| 038 | [Novo Carro](038-novo-carro) | Padrão Facade (estrutural) |
| 039 | [Cafeteria Leve](039-cafeteria-leve) | Padrão Flyweight (estrutural) |
| 040 | [Magos e Torres](040-magos-e-torres) | Padrão Proxy (estrutural) |

## Se você precisa…, peça um…

Use esta tabela para reconhecer o problema e saber que nome dar a ele num pedido à IA. O mapa de todos os padrões está no [guia](../guia/05-mapa-problema-padrao.md).

| Se você precisa… | Peça um… | Aula |
|---|---|---|
| Usar uma classe pronta cuja interface não é a que o sistema espera | **Adapter** | [031](031-quem-nao-tem-cao) |
| Duas dimensões variam de forma independente (ex.: forma × cor) e a herança explodiria em subclasses | **Bridge** | [032](032-formas-coloridas), [033](033-questionario) |
| Tratar um item e um grupo de itens da mesma forma, numa árvore | **Composite** | [034](034-empacotamento) |
| Acrescentar responsabilidades a um objeto em tempo de execução, em camadas | **Decorator** | [035](035-cafeteria), [036](036-arvore-de-natal) |
| Oferecer uma porta de entrada simples para um subsistema complicado | **Facade** | [037](037-novo-formas-coloridas), [038](038-novo-carro) |
| Muitos objetos parecidos gastam memória demais; compartilhar a parte comum | **Flyweight** | [039](039-cafeteria-leve) |
| Controlar o acesso a um objeto (permissão, carga tardia, registro) sem mudar quem o usa | **Proxy** | [040](040-magos-e-torres) |

[← Voltar ao sumário do curso](../README.md#sumário)
