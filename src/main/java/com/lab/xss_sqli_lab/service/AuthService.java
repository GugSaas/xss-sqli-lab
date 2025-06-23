package com.lab.xss_sqli_lab.service;

import com.lab.xss_sqli_lab.dto.LoginDTO;
import com.lab.xss_sqli_lab.dto.RegisterDTO;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void register(RegisterDTO dto) {
        if (userRepository.existsByCpf(dto.cpf)) {
            throw new IllegalArgumentException("CPF já registrado");
        }
        if (userRepository.existsByEmail(dto.email)) {
            throw new IllegalArgumentException("E-mail já registrado");
        }
        if (!dto.senha.equals(dto.confirmarSenha)) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }

        User user = new User();
        user.setNome(dto.nome);
        user.setEmail(dto.email);
        user.setCpf(dto.cpf);
        user.setDataNascimento(dto.dataNascimento);
        user.setCelular(dto.celular);
        user.setPais(dto.pais);
        user.setPasswordHash(encoder.encode(dto.senha));

        userRepository.save(user);
    }

    public boolean authenticate(LoginDTO dto) {
        return userRepository.findByCpf(dto.cpf)
                .map(user -> encoder.matches(dto.senha, user.getPasswordHash()))
                .orElse(false);
    }
}
