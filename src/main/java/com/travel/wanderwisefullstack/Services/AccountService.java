package com.travel.wanderwisefullstack.Services;



import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;

import java.util.List;

public interface AccountService {

    AppUser addNewUser(AppUser user);
    AppRole addNewRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}