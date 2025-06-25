package com.lab.xss_sqli_lab.controller;

import com.lab.xss_sqli_lab.dto.UpdateProfileDTO;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO dto) {
        try {
            String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            userService.updateProfileUnsafe(cpf, dto.getNome(), dto.getCelular());

            return ResponseEntity.ok(Map.of("message", "Perfil atualizado com sucesso"));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage(), "stacktrace", sw.toString()));
        }
    }
}
