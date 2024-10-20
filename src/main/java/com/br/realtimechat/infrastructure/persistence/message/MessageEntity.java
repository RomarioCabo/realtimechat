package com.br.realtimechat.infrastructure.persistence.message;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensagens")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mensagem", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "conteudo_mensagem", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_remetente_mensagem")
    private UserEntity sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_destinatario_mensagem")
    private UserEntity recipient;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public MessageEntity(final UUID idMessageChat, final MessageRequest messageRequest, final UserEntity sender,
                         final UserEntity recipient) {
        this.id = idMessageChat;
        this.content = messageRequest.getContent();
        this.sender = sender;
        this.recipient = recipient;
        this.createdAt = LocalDateTime.now();
    }

    public MessageResponse toModel() {
        return MessageResponse.builder()
                .id(this.id)
                .content(this.content)
                .sender(this.sender.toModel())
                .recipient(this.recipient.toModel())
                .createdAt(this.createdAt)
                .build();
    }
}
