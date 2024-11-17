package org.example.security3.servicee;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.security3.models.UserSecurity;
import org.example.security3.repos.SecurityRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityRepo securityRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity userSecurity = null;
        if (securityRepo.findByUsername(username).isPresent()) {
            log.info("user found in userdetails service");
            userSecurity = securityRepo.findByUsername(username).get();

            return new User(userSecurity.getUsername(), userSecurity.getPassword(), userSecurity.getRole().getPermissionSet().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
                    collect(Collectors.toSet()));
        }
        log.error("user not found in userdetails service");
        throw new UsernameNotFoundException("ПОЛЬЗОВАТЕЛЬ НЕ НАЙДЕН");
    }
}
