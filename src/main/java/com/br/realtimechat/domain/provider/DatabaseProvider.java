package com.br.realtimechat.domain.provider;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;

import java.util.UUID;

public interface DatabaseProvider {
    UserResponse saveUser(final UserRequest userRequest, final UUID idUser);
    UserResponse findUserByPhone(final String phone);
    MessageResponse saveMessage(final MessageRequest messageRequest, final UUID idMessageChat);
}
