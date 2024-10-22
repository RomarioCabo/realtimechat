package com.br.realtimechat.domain.user;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String phone;
}
