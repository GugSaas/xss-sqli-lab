package com.lab.xss_sqli_lab.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCreateDTO {
    @NotBlank(message = "Mensagem não pode estar vazia")
    private String mensagem;
}
