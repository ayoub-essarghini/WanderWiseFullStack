package com.travel.wanderwisefullstack.Repositories;

import com.travel.wanderwisefullstack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
