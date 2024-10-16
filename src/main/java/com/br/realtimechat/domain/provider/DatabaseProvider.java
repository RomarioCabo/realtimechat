package com.br.realtimechat.domain.provider;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;

import java.util.UUID;

public interface DatabaseProvider {
    MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat);
}
