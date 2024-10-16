package com.br.realtimechat.domain.chat.service.impl;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;
import com.br.realtimechat.domain.chat.service.MessageService;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final DatabaseProvider databaseProvider;

    @Override
    public MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat) {
        return databaseProvider.saveMessage(messageRequest, idMessageChat);
    }

    @Override
    public MessageResponse addUserToChat(final MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor) {
        // Adiciona o nome do usuário na sessão do WebSocket
        headerAccessor.getSessionAttributes().put("username", messageRequest.getSender());

        // Cria uma resposta que indica a entrada do usuário no chat
        MessageResponse messageResponse = MessageResponse.builder()
                .id(UUID.randomUUID())
                .content("User joined: " + messageRequest.getSender())
                .sender(messageRequest.getSender())
                .createdAt(LocalDateTime.now())
                .build();

        log.info("User {} joined the chat", messageRequest.getSender());

        return messageResponse;
    }
}
