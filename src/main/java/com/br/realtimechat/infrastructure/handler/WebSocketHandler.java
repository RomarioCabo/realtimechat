package com.br.realtimechat.infrastructure.handler;

import com.br.realtimechat.domain.message.MessageRequest;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions;
    private final ObjectMapper objectMapper;

    private final DatabaseProvider databaseProvider;

    public WebSocketHandler(@Qualifier("defaultObjectMapper") ObjectMapper objectMapper,
                            DatabaseProvider databaseProvider) {
        this.objectMapper = objectMapper;
        this.databaseProvider = databaseProvider;
        this.sessions = new ConcurrentHashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String username = Objects.requireNonNull(session.getUri()).getQuery().split("=")[1];
        sessions.put(username, session);
        log.info("[afterConnectionEstablished] User {} connected with session id {}", username, session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        log.info("[handleTextMessage] message {}", message.getPayload());

        MessageRequest messageRequest = objectMapper.readValue(message.getPayload(), MessageRequest.class);

        String targetUser = messageRequest.getIdRecipient().toString();
        String messageContent = messageRequest.getContent();

        WebSocketSession targetSession = sessions.get(targetUser);

        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage(messageContent));
            log.info("Message sent to user {}", targetUser);

            databaseProvider.saveMessage(messageRequest, null);
        } else {
            log.warn("User {} is not connected", targetUser);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.values().remove(session);
        log.info("[afterConnectionClosed] Session id {} disconnected", session.getId());
    }
}
