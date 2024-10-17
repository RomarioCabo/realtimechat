package com.br.realtimechat.controller.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final static String TITLE = "Ops! Ocorreu um erro";

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleRuntimeException(Exception ex) {
        ErrorHttpResponseDto errorHttpResponseDto = new ErrorHttpResponseDto(
                INTERNAL_SERVER_ERROR.toString(),
                TITLE,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .contentType(APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleBadRequestException(BadRequestException ex) {
        ErrorHttpResponseDto errorHttpResponseDto = new ErrorHttpResponseDto(
                BAD_REQUEST.toString(),
                TITLE,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }
}
