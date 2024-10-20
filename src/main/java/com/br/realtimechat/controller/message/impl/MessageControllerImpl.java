package com.br.realtimechat.controller.message.impl;

import com.br.realtimechat.controller.message.MessageController;
import com.br.realtimechat.domain.message.MessageGroup;
import com.br.realtimechat.domain.message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageControllerImpl implements MessageController {

    private MessageService messageService;

    @Override
    public ResponseEntity<List<MessageGroup>> getMessagesOfConversation(final UUID idRecipient, final UUID idSender) {
        return ResponseEntity.ok(messageService.getMessagesOfConversation(idRecipient, idSender));
    }
}
