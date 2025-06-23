package com.lab.xss_sqli_lab.dto;

import jakarta.validation.constraints.*;

public class LoginDTO {
    @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    public String cpf;

    @NotBlank
    public String senha;
}

