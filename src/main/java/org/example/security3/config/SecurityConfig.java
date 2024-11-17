package org.example.security3.config;


import lombok.RequiredArgsConstructor;
import org.example.security3.servicee.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/* Когда вы завершаете сессию, это означает, что вы удаляете все данные,
связанные с текущей сессией пользователя на сервере. В большинстве случаев это включает в себя удаление
объекта сессии, который хранит информацию о
 пользователе, его аутентификации и любые другие данные, которые могут быть связаны с этой сессией. */


/*Очистка аутентификации означает, что вы удаляете информацию о текущем состоянии аутентификации пользователя.
 Это может включать в себя
удаление токена аутентификации или других данных, которые указывают на то, что пользователь вошел в систему. */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
                antMatchers("/api2/reg").permitAll()
                .antMatchers("/api2/login").permitAll()
                .antMatchers("/api/save").hasAnyAuthority("write", "read")
                .antMatchers("/api/del/**").hasAuthority("write")
                .antMatchers("/api/cars").authenticated()
                .and().httpBasic().and()
                .logout()
                .logoutUrl("/api2/logout") // URL для выхода
                .logoutSuccessUrl("/api2/login") // URL, на который будет перенаправлен пользователь после выхода
                .invalidateHttpSession(true) // завершить сессию
                .clearAuthentication(true); // очистить аутентификацию;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
