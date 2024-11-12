package com.travel.wanderwisefullstack.Services.user;



import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;

import java.util.List;

public interface AccountService {

    AppUser addNewUser(AppUser user);
    void addNewRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}