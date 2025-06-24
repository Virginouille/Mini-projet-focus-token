package com.exemple.exotoken.init;

import com.exemple.exotoken.model.User;
import com.exemple.exotoken.repository.UserRepository;
import com.exemple.exotoken.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Initializing Data");

        User user1 = User.builder()
            .prenom("user1")
                .password("refeqergerg")
                .role("admin")
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .prenom("user2")
                .password("refertgerg")
                .role("user")
                .build();
        userRepository.save(user2);

    }
}
