package com.br.realtimechat.domain.chat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private UUID id;
    private String content;
    private String sender;
    private LocalDateTime createdAt;
}
