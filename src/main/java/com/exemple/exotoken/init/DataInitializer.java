package com.exemple.exotoken.init;

import com.exemple.exotoken.model.RoleEnum;
import com.exemple.exotoken.model.User;
import com.exemple.exotoken.repository.UserRepository;
import com.exemple.exotoken.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing Data");

        userRepository.deleteAll();
        userRepository.flush();

        userService.createUser("admin1", "admindededededed", RoleEnum.ROLE_ADMIN);
        userService.createUser("user1", "admindededededed", RoleEnum.ROLE_USER);
    }
}
