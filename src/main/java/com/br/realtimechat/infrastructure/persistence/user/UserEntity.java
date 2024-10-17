package com.br.realtimechat.infrastructure.persistence.user;

import com.br.realtimechat.domain.user.UserRequest;
import com.br.realtimechat.domain.user.UserResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome_usuario", nullable = false)
    private String name;

    @Column(name = "telefone_usuario", nullable = false)
    private String phone;

    public UserEntity(final UserRequest userRequest, final UUID idUser) {
        this.id = idUser;
        this.name = userRequest.getName();
        this.phone = userRequest.getPhone();
    }

    public UserResponse toModel() {
        return UserResponse.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .build();
    }
}
