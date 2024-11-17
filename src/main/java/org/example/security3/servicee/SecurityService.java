package org.example.security3.servicee;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.security3.dto.RegistrationDto;
import org.example.security3.handler.RegistrationException;
import org.example.security3.models.Role;
import org.example.security3.models.UserSecurity;
import org.example.security3.repos.SecurityRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityService {

    private final SecurityRepo securityRepo;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public void registrate(RegistrationDto registrationDto) {
        UserSecurity userSecurity = objectMapper.convertValue(registrationDto, UserSecurity.class);
        if (!securityRepo.findByUsername(userSecurity.getUsername()).isPresent()) {
            userSecurity.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
            userSecurity.setRole(Role.USER);
            log.info("ВЫ УСПЕШНО ЗАРЕГИСТРИРОВАЛИСЬ");
            securityRepo.save(userSecurity);
        }else {

            log.error("ПОЛЬЗОВАТЕЛЬ УЖЕ ЕСТЬ" + userSecurity.getUsername());

            throw new RegistrationException("ТАКОЙ ПОЛЬЗОВАТЕЛЬ УЖЕ ЕСТЬ" + userSecurity.getUsername());
        }
    }
}
