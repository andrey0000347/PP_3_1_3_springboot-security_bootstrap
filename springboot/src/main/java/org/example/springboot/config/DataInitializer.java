package org.example.springboot.config;


import org.example.springboot.model.Role;
import org.example.springboot.model.User;
import org.example.springboot.repository.RoleRepo;
import org.example.springboot.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = new Role("ROLE_ADMIN");
            roleRepo.save(adminRole);

            Role userRole = new Role("ROLE_USER");
            roleRepo.save(userRole);

            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setAge((byte) 25);
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@mail.com");
            admin.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            userRepo.save(admin);

            User user = new User();
            user.setFirstName("user");
            user.setLastName("user");
            user.setAge((byte) 29);
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@mail.ru");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userRepo.save(user);
        };
    }
}
