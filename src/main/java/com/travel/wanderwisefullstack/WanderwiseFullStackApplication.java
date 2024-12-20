package com.travel.wanderwisefullstack;

import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WanderwiseFullStackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanderwiseFullStackApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(AccountService accountService)
    {
        return args -> {
            if (!adExist(accountService))
            {
                accountService.addNewRole(new AppRole(null,"ORGANIZER"));
                accountService.addNewRole(new AppRole(null,"ADMIN"));
                accountService.addNewUser(new AppUser(null,"admin", "1234", new ArrayList<>(),new ArrayList<>()));
                accountService.addRoleToUser("admin","ADMIN");


            }

        };
    }

    boolean adExist(AccountService accountService)
    {
        AppUser adminExist = accountService.loadUserByUsername("admin");
       return adminExist != null;
    }
}
