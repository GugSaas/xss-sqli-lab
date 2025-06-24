package com.lab.xss_sqli_lab.dto;

import com.lab.xss_sqli_lab.util.InputSanitizer;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    public String cpf;

    @NotBlank
    public String senha;


    public void sanitize() {
        this.cpf = InputSanitizer.sanitizeCpf(this.cpf);
        this.senha = this.senha != null ? this.senha.trim() : null;
    }
}

