package com.br.realtimechat.infrastructure.persistence.chat;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public MessageEntity(final MessageRequest messageRequest, final UUID idMessageChat) {
        this.id = idMessageChat;
        this.content = messageRequest.getContent();
        this.sender = messageRequest.getSender();
        this.createdAt = LocalDateTime.now();
    }

    public MessageResponse toModel() {
        return MessageResponse.builder()
                .id(this.id)
                .content(this.content)
                .sender(this.sender)
                .createdAt(this.createdAt)
                .build();
    }
}
