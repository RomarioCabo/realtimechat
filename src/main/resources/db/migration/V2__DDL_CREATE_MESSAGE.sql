CREATE TABLE mensagens
(
    id_mensagem              UUID NOT NULL,
    conteudo_mensagem        TEXT NOT NULL,
    id_remetente_mensagem    UUID NOT NULL,
    id_destinatario_mensagem UUID NOT NULL,
    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_mensagem PRIMARY KEY (id_mensagem),
    CONSTRAINT fk_remetente_mensagem FOREIGN KEY (id_remetente_mensagem) REFERENCES usuarios (id_usuario),
    CONSTRAINT fk_destinatario_mensagem FOREIGN KEY (id_destinatario_mensagem) REFERENCES usuarios (id_usuario)
);