package com.travel.wanderwisefullstack.Services.role;

import com.travel.wanderwisefullstack.models.AppRole;

import java.util.List;

public interface RoleService {

    AppRole save(AppRole appRole);
    AppRole findById(Long id);
    void deleteById(Long id);
    AppRole update(Long id, AppRole appRole);
    List<AppRole> findAll();
}
