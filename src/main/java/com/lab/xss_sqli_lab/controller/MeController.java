package com.lab.xss_sqli_lab.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.xss_sqli_lab.repository.UserRepository;

@RestController
@RequestMapping("/me")
public class MeController {

    private final UserRepository userRepository;

    public MeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getLoggedUser() {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByCpf(cpf)
                .map(user -> Map.of(
                        "nome", user.getNome(),
                        "email", user.getEmail()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuário não encontrado")));
    }
}

