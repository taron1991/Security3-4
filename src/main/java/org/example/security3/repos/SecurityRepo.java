package org.example.security3.repos;

import org.example.security3.models.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityRepo extends JpaRepository<UserSecurity,Integer> {
    Optional<UserSecurity> findByUsername(String username);
}
