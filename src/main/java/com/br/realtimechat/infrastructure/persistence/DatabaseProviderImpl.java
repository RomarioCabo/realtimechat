package com.br.realtimechat.infrastructure.persistence;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;
import com.br.realtimechat.infrastructure.persistence.message.MessageEntity;
import com.br.realtimechat.infrastructure.persistence.message.MessageRepository;
import com.br.realtimechat.infrastructure.persistence.user.UserEntity;
import com.br.realtimechat.infrastructure.persistence.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DatabaseProviderImpl implements DatabaseProvider {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public UserResponse saveUser(final UserRequest userRequest, final UUID idUser) {
        final UserEntity userEntity = new UserEntity(userRequest, idUser);
        final UserEntity savedUser = userRepository.save(userEntity);

        return savedUser.toModel();
    }

    @Override
    public UserResponse findUserByPhone(final String phone) {
        return Optional.ofNullable(userRepository.findByPhone(phone))
                .map(UserEntity::toModel)
                .orElse(null);
    }

    @Override
    @Transactional
    public MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat) {
        final UserEntity sender = userRepository.findUserById(messageRequest.getIdSender());
        final UserEntity recipient = userRepository.findUserById(messageRequest.getIdRecipient());

        final MessageEntity messageEntity = new MessageEntity(idMessageChat, messageRequest, sender, recipient);
        return messageRepository.save(messageEntity).toModel();
    }
}
