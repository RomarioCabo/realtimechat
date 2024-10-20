package com.br.realtimechat.domain.provider;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface DatabaseProvider {
    UserResponse saveUser(final UserRequest userRequest, final UUID idUser);
    UserResponse findUserByPhone(final String phone);
    void saveMessage(final MessageRequest messageRequest, final UUID idMessageChat);
    List<MessageResponse> getAllMessagesSentByMeToSpecificRecipient(final UUID idSender, final UUID idRecipient);
    List<MessageResponse> getAllMessagesSendToMeToSpecificRecipient(final UUID idRecipient, final UUID idSender);
}
