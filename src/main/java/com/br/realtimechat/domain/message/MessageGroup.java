package com.br.realtimechat.domain.message;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageGroup {
    private LocalDate grouping;
    private List<MessageResponse> messagesSendToMeToSpecificRecipient;
    private List<MessageResponse> messagesSentByMeToSpecificRecipient;
}
