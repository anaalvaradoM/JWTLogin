package com.JWTClase.JWTLogin.repository;

import com.JWTClase.JWTLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    //Metodos personalizados
    User findByUsername(String username);
}
