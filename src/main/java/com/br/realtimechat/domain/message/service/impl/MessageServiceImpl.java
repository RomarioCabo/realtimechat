package com.br.realtimechat.domain.message.service.impl;

import com.br.realtimechat.domain.message.MessageGroup;
import com.br.realtimechat.domain.message.MessageResponse;
import com.br.realtimechat.domain.message.service.MessageService;
import com.br.realtimechat.domain.provider.DatabaseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final DatabaseProvider databaseProvider;

    public List<MessageGroup> getMessagesOfConversation(final UUID idRecipient, final UUID idSender) {
        List<MessageResponse> messagesSentByMeToSpecificRecipient = databaseProvider.getAllMessagesSentByMeToSpecificRecipient(idSender, idRecipient);
        List<MessageResponse> messagesSendToMeToSpecificRecipient = databaseProvider.getAllMessagesSendToMeToSpecificRecipient(idRecipient, idSender);

        Map<LocalDate, List<MessageResponse>> mapMessagesSentByMeToSpecificRecipient = messagesSentByMeToSpecificRecipient.stream()
                .collect(Collectors.groupingBy(message -> message.getCreatedAt().toLocalDate()));

        Map<LocalDate, List<MessageResponse>> mapMessagesSendToMeToSpecificRecipient = messagesSendToMeToSpecificRecipient.stream()
                .collect(Collectors.groupingBy(message -> message.getCreatedAt().toLocalDate()));

        // Criar um conjunto com todas as datas únicas
        Set<LocalDate> allDates = Stream.concat(mapMessagesSentByMeToSpecificRecipient.keySet().stream(), mapMessagesSendToMeToSpecificRecipient.keySet().stream())
                .collect(Collectors.toSet());

        // Iterar pelas datas e criar grupos de mensagens
        List<MessageGroup> messageGroups = allDates.stream().map(date -> {
            List<MessageResponse> sentByMe = mapMessagesSentByMeToSpecificRecipient.getOrDefault(date, Collections.emptyList());
            List<MessageResponse> sentToMe = mapMessagesSendToMeToSpecificRecipient.getOrDefault(date, Collections.emptyList());

            return MessageGroup.builder()
                    .grouping(date)
                    .messagesSentByMeToSpecificRecipient(sentByMe)
                    .messagesSendToMeToSpecificRecipient(sentToMe)
                    .build();
        }).toList();

        return messageGroups;
    }
}
