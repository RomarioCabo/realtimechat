package com.br.realtimechat.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MessageGroup {
    private LocalDate grouping;
    @JsonIgnoreProperties({"sender", "recipient"})
    private List<MessageResponse> messagesSendToMeToSpecificRecipient;
    @JsonIgnoreProperties({"sender", "recipient"})
    private List<MessageResponse> messagesSentByMeToSpecificRecipient;
}
