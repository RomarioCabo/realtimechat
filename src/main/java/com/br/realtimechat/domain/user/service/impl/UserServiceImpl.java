package com.br.realtimechat.domain.user.service.impl;

import com.br.realtimechat.controller.exception.BadRequestException;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;
import com.br.realtimechat.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DatabaseProvider databaseProvider;

    @Override
    public UserResponse upsert(final UserRequest userRequest, final UUID userId) {
        phoneExists(userRequest.getPhone());
        return databaseProvider.saveUser(userRequest, userId);
    }

    private void phoneExists(final String phone) {
        Optional.ofNullable(databaseProvider.findUserByPhone(phone)).ifPresent(user -> {
            throw new BadRequestException(String.format("O número: %s de telefone já existe!", user.getPhone()));
        });
    }
}
