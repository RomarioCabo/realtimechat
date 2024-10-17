package com.br.realtimechat.controller.message;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageRequest messageRequest) {
        showLogger(messageRequest.getIdSender(), messageRequest.getIdRecipient(), messageRequest.getContent());

        messagingTemplate.convertAndSendToUser(
                messageRequest.getIdRecipient().toString(),
                "/queue/messages",
                messageService.saveMessage(messageRequest, null) // Mensagem a ser enviada
        );
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor) {
        showLogger(messageRequest.getIdSender(), messageRequest.getIdRecipient(), messageRequest.getContent());
        messageService.addUserToChat(messageRequest, headerAccessor);
    }

    private void showLogger(final UUID idSender, final UUID idRecipient, final String content) {
        log.info("Mensagem recebida - sender: {}, recipient {}, content {}", idSender, idRecipient, content);
    }
}
