package com.br.realtimechat.infrastructure.persistence;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import com.br.realtimechat.infrastructure.persistence.chat.MessageEntity;
import com.br.realtimechat.infrastructure.persistence.chat.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DatabaseProviderImpl implements DatabaseProvider {

    private final MessageRepository messageRepository;

    @Override
    public MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat) {
        final MessageEntity messageEntity = new MessageEntity(messageRequest, idMessageChat);
        final MessageEntity savedMessageEntity = messageRepository.save(messageEntity);

        return savedMessageEntity.toModel();
    }
}
