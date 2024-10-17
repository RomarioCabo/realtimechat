package com.br.realtimechat.domain.user.service;

import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse upsert(final UserRequest userRequest, final UUID userId);
}
