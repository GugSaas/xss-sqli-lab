package com.lab.xss_sqli_lab.controller;

import com.lab.xss_sqli_lab.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
                        "nome", user.getNome()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuário não encontrado")));
    }
}

