package com.travel.wanderwisefullstack.Repositories;


import com.travel.wanderwisefullstack.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}