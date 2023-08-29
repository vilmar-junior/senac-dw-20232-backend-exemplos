INSERT INTO exemplos.fabricantes (nome)
VALUES('Ouro');

INSERT INTO exemplos.fabricantes (nome)
VALUES('Pelé');

INSERT INTO exemplos.fabricantes (nome)
VALUES('Havaianas');

INSERT INTO exemplos.fabricantes (nome)
VALUES('Rider');

INSERT INTO exemplos.produtos (nome, id_fabricante, valor, peso, data_cadastro)
VALUES('Café', 1, 12.5, 0.5, sysdate());

INSERT INTO exemplos.produtos (nome, id_fabricante, valor, peso, data_cadastro)
VALUES('Café', 2, 10.0, 0.5, sysdate());

INSERT INTO exemplos.produtos (nome, id_fabricante, valor, peso, data_cadastro)
VALUES('Chinela de tira', 3, 80.5, 0.2, sysdate());

INSERT INTO exemplos.produtos (nome, id_fabricante, valor, peso, data_cadastro)
VALUES('Chinela pesada', 4, 70.2, 0.3, sysdate());
