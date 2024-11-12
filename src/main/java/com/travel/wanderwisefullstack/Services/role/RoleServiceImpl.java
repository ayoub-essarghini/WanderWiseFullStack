package com.travel.wanderwisefullstack.Services.role;

import com.travel.wanderwisefullstack.Repositories.RoleRepository;
import com.travel.wanderwisefullstack.Response.UserAlreadyExistsException;
import com.travel.wanderwisefullstack.models.AppRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public AppRole save(AppRole appRole) {

        if (roleRepository.findByName(appRole.getName()) != null) {
            throw new UserAlreadyExistsException("Role with name " + appRole.getName() + " already exists");
        }

        return roleRepository.save(appRole);
    }

    @Override
    public AppRole findById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new UserAlreadyExistsException("Not found"));
    }

    @Override
    public void deleteById(Long id) {
        AppRole role = findById(id);
        if (role.getName().equals("ADMIN")) {
            throw new UserAlreadyExistsException("Cannot delete admin role");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public AppRole update(Long id, AppRole appRole) {
            if (roleRepository.findById(id).isPresent()) {
                AppRole role = roleRepository.findById(id).get();
                role.setName(appRole.getName());
                return roleRepository.save(role);
            }
            throw new UserAlreadyExistsException("Not found");
    }


    @Override
    public List<AppRole> findAll() {
        return roleRepository.findAll();
    }
}
