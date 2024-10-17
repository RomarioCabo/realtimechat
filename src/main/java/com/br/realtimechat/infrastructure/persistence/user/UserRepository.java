package com.br.realtimechat.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Transactional(readOnly = true)
    @Query("SELECT ue FROM UserEntity ue WHERE ue.phone = :phone")
    UserEntity findByPhone(@Param("phone") String phone);

    @Transactional(readOnly = true)
    @Query("SELECT ue FROM UserEntity ue WHERE ue.id = :id")
    UserEntity findUserById(@Param("id") UUID id);
}
