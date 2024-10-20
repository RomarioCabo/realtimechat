package com.br.realtimechat.infrastructure.persistence.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

    //enviado para miM
    @Transactional(readOnly = true)
    @Query("SELECT me FROM MessageEntity me WHERE me.sender.id = :idSender AND me.recipient.id = :idRecipient ORDER BY me.createdAt")
    List<MessageEntity> getAllMessagesSentByMeToSpecificRecipient(@Param("idSender") UUID idSender, @Param("idRecipient") UUID idRecipient);

    //enviado por mim
    @Transactional(readOnly = true)
    @Query("SELECT me FROM MessageEntity me WHERE me.recipient.id = :idRecipient AND me.sender.id = :idSender ORDER BY me.createdAt")
    List<MessageEntity> getAllMessagesSendToMeToSpecificRecipient(@Param("idSender") UUID idSender, @Param("idRecipient") UUID idRecipient);
}
