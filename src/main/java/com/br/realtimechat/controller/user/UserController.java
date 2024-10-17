package com.br.realtimechat.controller.user;

import com.br.realtimechat.controller.exception.ErrorHttpResponseDto;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tags(@Tag(name = "Usuário", description = "Usuário REST Controller"))
public interface UserController {

    @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso.",
            content = {@Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserResponse.class))
            })
    @ApiResponse(
            responseCode = "400",
            description = "Bad request.",
            content = {@Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorHttpResponseDto.class))
            })
    @PostMapping(
            value = "/usuario/salvar",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> save(@RequestBody UserRequest request);
}
