# Projeto tudo de bom

## Pré-requisitos
java 21

## como rodar o projeto localmente

Após clonar o projeto em seu ambiente local, siga os passos abaixo:

## MySQL:

1 - Abra o arquivo aplication.properties e coloque esses dados:

spring.jpa.hibernate.ddl-auto=fals
spring.datasource.url=jdbc:mysql://localhost:3306/<nome_banco>
spring.datasource.username=<usuario>
spring.datasource.password=<senha>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

2 - rode esses sqls no banco:

- CREATE TABLE users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL,
cpf VARCHAR(11) NOT NULL,
senha VARCHAR(255) NOT NULL,
data_nascimento DATE NOT NULL,
estado VARCHAR(255) NOT NULL,
cidade VARCHAR(255) NOT NULL,
endereco VARCHAR(255) NOT NULL,
role VARCHAR(255) NOT NULL,
data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
nome VARCHAR(255) NOT NULL,
sobrenome VARCHAR(255) NOT NULL
);


- CREATE TABLE cup_cake (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
aprovado BIT NOT NULL,
descricao VARCHAR(255)NOT NULL,
ingredientes VARCHAR(500) NOT NULL,
modo_preparo VARCHAR(1000) NOT NULL,
nome VARCHAR(255) NOT NULL,
preco DECIMAL(38, 2) NOT NULL,
tempo_preparo INT NOT NULL,
url_imagem VARCHAR(255) NOT NULL,
usuario_id BIGINT NOT NULL,
FOREIGN KEY (usuario_id) REFERENCES USERS(id)
);


- CREATE TABLE favorito (
id_cup_cake BIGINT NOT NULL,
id_usuario BIGINT NOT NULL,
PRIMARY KEY (id_cup_cake, id_usuario)
);

## Após é só iniciar o projeto através de qualquer IDE