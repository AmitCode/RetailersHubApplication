package com.user.service.app.repositories;

import com.user.service.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserEmailId(String userEmailId);
    public void deleteByUserName(String userName);
}
