package org.example.security3.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationDto {

    @Size(min = 2,max = 20)
    @NotBlank
    private String username;
    @Size(min = 2,max = 200)
    @NotBlank
    private String password;
}
