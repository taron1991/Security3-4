package org.example.security3.controllerSecure;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.security3.dto.AuthenticationDto;
import org.example.security3.dto.RegistrationDto;
import org.example.security3.handler.RegistrationException;
import org.example.security3.servicee.SecurityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
@RequiredArgsConstructor
@Slf4j
public class SecureController {

    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/reg")
    public void saveAndRegistrate(@RequestBody @Valid RegistrationDto registrationDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error(" ВОЗНИКЛА ОШИБКА В МЕТОДЕ РЕГИСТРАЦИИ");
            throw new RegistrationException(bindingResult.getFieldError().getField());
        }
        securityService.registrate(registrationDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid AuthenticationDto authenticationDto,BindingResult bindingResult){
         if(bindingResult.hasErrors()){
             log.error(" ВОЗНИКЛА ОШИБКА В МЕТОДЕ login");
             throw new RegistrationException(bindingResult.getFieldError().getField());
         }
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),authenticationDto.getPassword());

        try {
            authenticationManager.authenticate(token);
        }
        catch (AuthenticationException e){
            log.error("ОШИБКА АУТЕНТИФИКАЦИИ");
            System.out.println(e.getMessage());
        }

        log.info("ВЫ УСПЕШНО ЗАЛОГИНИЛИСЬ");

    }

}
