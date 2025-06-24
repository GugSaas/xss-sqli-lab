package com.lab.xss_sqli_lab.service;

import com.lab.xss_sqli_lab.dto.LoginDTO;
import com.lab.xss_sqli_lab.dto.RegisterDTO;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void register(RegisterDTO dto) {
        dto.sanitize();

        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já registrado");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já registrado");
        }
        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }

        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setDataNascimento(dto.getDataNascimento());
        user.setCelular(dto.getCelular());
        user.setPais(dto.getPais());
        user.setPasswordHash(encoder.encode(dto.getSenha()));

        userRepository.save(user);
    }

    public Optional<User> authenticateAndGetUser(LoginDTO dto) {
        dto.sanitize();

        return userRepository.findByCpf(dto.getCpf())
                .filter(user -> encoder.matches(dto.getSenha(), user.getPasswordHash()));
    }
}


