package com.travel.wanderwisefullstack.Controllers;


import com.travel.wanderwisefullstack.Repositories.RoleRepository;
import com.travel.wanderwisefullstack.Services.role.RoleService;
import com.travel.wanderwisefullstack.models.AppRole;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    @PostMapping(path = "/add-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AppRole addRole(@RequestBody AppRole appRole) {
        return roleService.save(appRole);
    }

    @GetMapping(path = "/get-role/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AppRole> getRole(@PathVariable Long id) {

        return ResponseEntity.ok(roleService.findById(id));

    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AppRole> getAllRoles() {
        return roleService.findAll();
    }

    @PostMapping(path = "/update/{id}")
    public AppRole updateRole(@PathVariable Long id, @RequestBody AppRole appRole) {
        return roleService.update(id, appRole);
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
