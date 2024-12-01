package org.valentine.goldspoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.valentine.goldspoon.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
