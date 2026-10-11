// Primeira aplicação Java, na forma do Java 25: escreve "Olá, Mundo!!!"
// na saída padrão.
//
// Este é um arquivo compacto: não declara classe nem pacote. O compilador
// cria uma classe implícita a partir do arquivo, e o método main pode ser
// de instância, sem public, static nem parâmetros.
//
// Requer o JDK 25 ou mais recente. Para executar, na raiz do repositório:
//     java 01-fundamentos/001-ola-mundo/java25/OlaMundo.java
//
// Este arquivo fica fora de src/main/java, e por isso o Maven não o compila:
// o curso compila em Java 17, em que esta forma não existe.
//
// Autor: profandrade@gmail.com

void main() {
    IO.println("Olá, Mundo!!!");
}
