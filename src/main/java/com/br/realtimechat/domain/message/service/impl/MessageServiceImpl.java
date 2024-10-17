package com.br.realtimechat.domain.message.service.impl;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.domain.message.service.MessageService;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

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
    public void addUserToChat(final MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor) {
        log.info("User {} joined the chat", messageRequest.getIdSender());
        // Adiciona o nome do usuário na sessão do WebSocket
        headerAccessor.getSessionAttributes().put("username", messageRequest.getIdSender());
    }
}
