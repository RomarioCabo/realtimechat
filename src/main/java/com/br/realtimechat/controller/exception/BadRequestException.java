package com.br.realtimechat.controller.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
