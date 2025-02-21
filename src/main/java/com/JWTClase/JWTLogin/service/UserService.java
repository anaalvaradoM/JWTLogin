package com.JWTClase.JWTLogin.service;

import com.JWTClase.JWTLogin.model.User;
import com.JWTClase.JWTLogin.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        User userLogin = new User();
        userLogin.setUsername(user.getUsername());
        userLogin.setPassword(passwordEncoder.encode(user.getPassword()));
        // Encriptar la contraseña
        return userRepository.save(userLogin);
    }

    // Métdo de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
