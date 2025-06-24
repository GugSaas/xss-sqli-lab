package com.lab.xss_sqli_lab.dto;

import com.lab.xss_sqli_lab.util.InputSanitizer;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDTO {
    @NotBlank public String nome;

    @Email @NotBlank public String email;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos") @NotBlank
    public String cpf;

    @Past(message = "Data de nascimento deve ser no passado")
    @NotNull public LocalDate dataNascimento;

    @Pattern(regexp = "\\d{10,15}", message = "Celular deve conter apenas números") @NotBlank
    public String celular;

    @NotBlank
    public String pais;

    @NotBlank
    @Pattern(regexp = "\\d{6,8}", message = "Senha deve conter entre 6 e 8 dígitos numéricos")
    public String senha;

    @NotBlank public String confirmarSenha;

    public void sanitize() {
        this.nome = InputSanitizer.sanitizeString(this.nome);
        this.email = InputSanitizer.sanitizeEmail(this.email);
        this.cpf = InputSanitizer.sanitizeCpf(this.cpf);
        this.celular = InputSanitizer.sanitizeCelular(this.celular);
        this.pais = InputSanitizer.sanitizePais(this.pais);
        this.senha = this.senha != null ? this.senha.trim() : null;
        this.confirmarSenha = this.confirmarSenha != null ? this.confirmarSenha.trim() : null;
    }
}

