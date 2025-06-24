package com.lab.xss_sqli_lab.controller;

import com.lab.xss_sqli_lab.dto.LoginDTO;
import com.lab.xss_sqli_lab.dto.LoginResponseDTO;
import com.lab.xss_sqli_lab.dto.RegisterDTO;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok(Map.of("message", "Usuário registrado com sucesso"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto, HttpServletRequest request) {
        Optional<User> userOpt = authService.authenticateAndGetUser(dto);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            request.changeSessionId();

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getCpf(), null, List.of());

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);

            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    context
            );

            return ResponseEntity.ok(
                    new LoginResponseDTO("Login realizado com sucesso", user.getNome(), user.getEmail())
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciais inválidas"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "Logout realizado com sucesso"));
    }
}

