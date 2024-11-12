package com.travel.wanderwisefullstack.Controllers;


import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.dto.RoleUserForm;
import com.travel.wanderwisefullstack.models.AppUser;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/users")
    public List<AppUser> getUsers() {
        return accountService.listUsers();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/add-user")
    public AppUser saveUser(@RequestBody AppUser user){
        return accountService.addNewUser(user);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUserName(),roleUserForm.getRoleName());
    }

}

