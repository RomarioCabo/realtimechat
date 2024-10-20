package com.br.realtimechat.domain.message;

import com.br.realtimechat.domain.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private UUID id;
    private String content;
    private UserResponse sender;
    private UserResponse recipient;
    private LocalDateTime createdAt;
}
