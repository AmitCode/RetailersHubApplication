package com.notifications.repository;

import com.notifications.entities.Emails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Emails, Long> {
}
