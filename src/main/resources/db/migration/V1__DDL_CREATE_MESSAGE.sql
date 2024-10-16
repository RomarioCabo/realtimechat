CREATE TABLE message
(
    id         UUID         NOT NULL,
    content    TEXT         NOT NULL,
    sender     VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_data PRIMARY KEY (id)
);