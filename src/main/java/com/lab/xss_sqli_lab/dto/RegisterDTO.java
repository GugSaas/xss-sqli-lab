package com.lab.xss_sqli_lab.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class RegisterDTO {
    @NotBlank public String nome;

    @Email @NotBlank public String email;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos") @NotBlank
    public String cpf;

    @Past(message = "Data de nascimento deve ser no passado")
    @NotNull public LocalDate dataNascimento;

    @Pattern(regexp = "\\d{10,15}", message = "Celular deve conter apenas números") @NotBlank
    public String celular;

    @NotBlank public String pais;

    @NotBlank @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    public String senha;

    @NotBlank public String confirmarSenha;
}

