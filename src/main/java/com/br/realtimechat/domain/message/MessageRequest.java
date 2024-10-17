package com.br.realtimechat.domain.message;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String content;
    private UUID idSender;
    private UUID idRecipient;
}
