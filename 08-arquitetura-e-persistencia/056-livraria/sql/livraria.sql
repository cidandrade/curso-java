-- Banco de dados da aula 056 - Livraria
--
-- Script reconstruído por engenharia reversa de BD.java (método inicializa)
-- e das consultas das classes DAO. Não existia script SQL original.
--
-- Atenção: ao ser instanciada, a classe Controle chama BD.inicializa(),
-- que APAGA e RECRIA as tabelas abaixo com estes mesmos dados.
-- Este script serve para deixar o banco pronto (e documentado) antes disso.
--
-- Usuário e senha usados pela aplicação (fixos em BD.java):
--   URL: jdbc:mysql://localhost/livraria  usuário: cidandrade  senha: 123456

CREATE DATABASE IF NOT EXISTS livraria
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE livraria;

DROP TABLE IF EXISTS precos;
DROP TABLE IF EXISTS livros;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS livrarias;

CREATE TABLE livrarias (
    id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(10) NOT NULL,
    UNIQUE KEY (nome)
);

CREATE TABLE categorias (
    id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(15) NOT NULL,
    UNIQUE KEY (nome)
);

CREATE TABLE livros (
    id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idCat INT NOT NULL,
    nome  VARCHAR(30) NOT NULL,
    autor VARCHAR(25) NOT NULL,
    FOREIGN KEY (idCat) REFERENCES categorias (id),
    UNIQUE KEY (nome, autor)
);

-- Tabela sem chave primária, como no original
CREATE TABLE precos (
    idLivro    INT NOT NULL,
    idLivraria INT NOT NULL,
    data       DATE NOT NULL,
    preco      DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (idLivro) REFERENCES livros (id),
    FOREIGN KEY (idLivraria) REFERENCES livrarias (id)
);

INSERT INTO livrarias (nome) VALUES ('Amazonia'), ('Jangada'), ('Africanas');

INSERT INTO categorias (nome) VALUES ('Ficção'), ('Romance'), ('Esotéricos');

INSERT INTO livros (nome, autor, idCat) VALUES
    ('Lolita', 'Vladimir Nabokov', 2),
    ('Eu, Robô', 'Issac Azimov', 1),
    ('O Caminho do Tarot', 'Alejandro Jodorowsky', 3);

INSERT INTO precos (idLivro, idLivraria, data, preco) VALUES
    (1, 1, '2021-01-10', 45.60),
    (1, 2, '2021-01-11', 44.70),
    (1, 3, '2021-01-11', 48.12),
    (2, 1, '2022-03-26', 32.50),
    (2, 2, '2022-03-26', 33.90),
    (2, 3, '2022-03-26', 35.00),
    (3, 1, '2022-10-10', 92.50),
    (3, 2, '2022-10-10', 92.00),
    (3, 3, '2022-10-10', 87.48);
