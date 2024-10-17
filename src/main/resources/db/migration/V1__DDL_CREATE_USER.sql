CREATE TABLE usuarios
(
    id_usuario       UUID         NOT NULL,
    nome_usuario     VARCHAR(255) NOT NULL,
    telefone_usuario VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
);