package com.travel.wanderwisefullstack.Controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.dto.RoleUserForm;
import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static com.travel.wanderwisefullstack.JWTUtils.JWT_SECRET;
import static com.travel.wanderwisefullstack.JWTUtils.TIME_OUT_ACCESS_TOKEN;

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
    public AppUser saveUser(@RequestBody AppUser user) {
        return accountService.addNewUser(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUserName(), roleUserForm.getRoleName());
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            try {
                jwtToken = jwtToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
                String username = decodedJWT.getSubject();

                AppUser user = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + TIME_OUT_ACCESS_TOKEN)).withIssuer(request.getRequestURL().toString()).withClaim("roles", user.getRoles().stream().map(AppRole::getName).collect(Collectors.toList())).sign(algorithm);

                Map<String, String> tokenDetails = new HashMap<>();
                tokenDetails.put("access_token", jwtAccessToken);
                tokenDetails.put("refresh_token", jwtToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokenDetails);

            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

            }
        } else throw new RuntimeException("refresh token is invalid");
    }

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }


}

