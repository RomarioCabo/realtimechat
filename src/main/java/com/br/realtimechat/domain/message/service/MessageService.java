package com.br.realtimechat.domain.message.service;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.UUID;

public interface MessageService {

    MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat);
    void addUserToChat(final MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor);
}
