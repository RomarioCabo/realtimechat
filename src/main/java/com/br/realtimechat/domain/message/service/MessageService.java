package com.br.realtimechat.domain.message.service;

import com.br.realtimechat.domain.message.MessageGroup;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    List<MessageGroup> getMessagesOfConversation(final UUID idRecipient, final UUID idSender);
}
