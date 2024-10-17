package com.br.realtimechat.controller.user.impl;

import com.br.realtimechat.controller.user.UserController;
import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;
import com.br.realtimechat.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> save(final UserRequest request) {
        UserResponse userResponse = userService.upsert(request, null);
        return ResponseEntity.created(null).body(userResponse);
    }
}
