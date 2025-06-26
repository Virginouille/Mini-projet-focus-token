package com.exemple.exotoken.service;

import com.exemple.exotoken.model.RoleEnum;
import com.exemple.exotoken.model.Token;
import com.exemple.exotoken.model.User;
import com.exemple.exotoken.repository.TokenRepository;
import com.exemple.exotoken.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    //Injection UserRepository
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;


    /**Méthode create user*/
    @Transactional
    public User createUser(String username, String password, RoleEnum role) {

        //Création user avec username, password et role en enum
        User user = new User();
        user.setUsername(username);
        String motDePasse = passwordEncoder.encode(password);
        user.setPassword(motDePasse);
        user.setRole(role);

        userRepository.save(user);

        //Générer token
        String jwtToken = jwtService.generateToken(username);

        //Lier troken à user et save dans tokenrepo
        Token tokenEntity = new Token();
        tokenEntity.setToken(jwtToken);
        tokenEntity.setUser(user);
        tokenRepository.save(tokenEntity);

        return user;
    }

}
