package com.br.realtimechat.controller.message;

import com.br.realtimechat.controller.exception.ErrorHttpResponseDto;
import com.br.realtimechat.domain.message.MessageGroup;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tags(@Tag(name = "Mensagens por conversa", description = "Mensagens REST Controller"))
public interface MessageController {

    @ApiResponse(
            responseCode = "200",
            description = "Mensagens(s) encontrado.",
            content = {@Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = MessageGroup.class)))
            })
    @ApiResponse(
            responseCode = "400",
            description = "Bad request.",
            content = {@Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorHttpResponseDto.class))
            })
    @GetMapping(value = "/mensagens/{idRecipient}/{idSender}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<MessageGroup>> getMessagesOfConversation(@PathVariable UUID idRecipient, @PathVariable UUID idSender);
}
