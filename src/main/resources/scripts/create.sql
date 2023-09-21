drop schema EXEMPLOS;

CREATE SCHEMA EXEMPLOS;
USE EXEMPLOS;

CREATE TABLE exemplos.fabricantes (
	id int auto_increment NOT NULL,
	nome varchar(400) NOT NULL,
	cnpj varchar(14) NOT NULL,
	CONSTRAINT fabricantes_pk PRIMARY KEY (id)
);

CREATE TABLE exemplos.produtos (
	id int auto_increment NOT NULL,
	nome varchar(400) NOT NULL,
	id_fabricante int NOT NULL,
	valor DECIMAL NOT NULL,
	peso DECIMAL NOT NULL,
	data_cadastro DATE NOT NULL,
	CONSTRAINT produtos_pk PRIMARY KEY (id),
	CONSTRAINT id_fabricante foreign key (id_fabricante) references exemplos.fabricantes(ID)
);

-- Alterações da prova
-- Criar as novas colunas em FABRICANTES
alter table fabricantes add column cidade VARCHAR(255);
alter table fabricantes add column cep VARCHAR(8);
alter table fabricantes add column uf VARCHAR(2);
