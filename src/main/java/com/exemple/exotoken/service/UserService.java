package com.exemple.exotoken.service;

import com.exemple.exotoken.model.User;
import com.exemple.exotoken.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    //Injection UserRepository
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**Méthode create user*/
    @Transactional
    public User createUser(String username, String password) {
        User user = new User();
        user.setPrenom(username);
        String motDePasse = passwordEncoder.encode(password);
        user.setPassword(motDePasse);
        return userRepository.save(user);
    }

}
