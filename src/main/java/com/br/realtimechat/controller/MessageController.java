package com.br.realtimechat.controller;

import com.br.realtimechat.domain.chat.MessageRequest;
import com.br.realtimechat.domain.chat.MessageResponse;
import com.br.realtimechat.domain.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageResponse sendMessage(@Payload MessageRequest messageRequest) {
        log.info("Menssagem recebida - sender: {}, content {}", messageRequest.getSender(), messageRequest.getContent());

        return messageService.saveMessage(messageRequest, null);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageResponse addUser(@Payload MessageRequest messageRequest, SimpMessageHeaderAccessor headerAccessor) {
        log.info("Menssagem recebida - sender: {}, content {}", messageRequest.getSender(), messageRequest.getContent());

        return messageService.addUserToChat(messageRequest, headerAccessor);
    }

}
