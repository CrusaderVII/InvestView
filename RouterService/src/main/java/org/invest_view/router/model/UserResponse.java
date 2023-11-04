package org.invest_view.router.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
}
