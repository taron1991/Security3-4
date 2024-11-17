package org.example.security3.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usersecurity")
@NoArgsConstructor
@Getter
@Setter
public class UserSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String username;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
