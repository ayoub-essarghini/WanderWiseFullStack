package com.travel.wanderwisefullstack.Services.user;


import com.travel.wanderwisefullstack.Repositories.RoleRepository;
import com.travel.wanderwisefullstack.Repositories.UserRepository;
import com.travel.wanderwisefullstack.Response.UserAlreadyExistsException;
import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;

    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        _userRepository = userRepository;
        _roleRepository = roleRepository;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewUser(AppUser user) {
        String pwd = user.getPassword();
        user.setPassword(_passwordEncoder.encode(pwd));

        AppUser existingUser = _userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
        }

        return _userRepository.save(user);
    }


    @Override
    public AppRole addNewRole(AppRole role) {
        return _roleRepository.save(role);
    }



    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = _userRepository.findByUsername(username);
        AppRole role = _roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return _userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return _userRepository.findByUsernameNot(currentUsername);
    }
}