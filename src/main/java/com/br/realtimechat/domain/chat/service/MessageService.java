package com.br.realtimechat.domain.chat.service;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.UUID;

public interface MessageService {

    MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat);
    MessageResponse addUserToChat(final MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor);
}
