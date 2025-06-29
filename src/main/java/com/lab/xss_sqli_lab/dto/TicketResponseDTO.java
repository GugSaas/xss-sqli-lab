package com.lab.xss_sqli_lab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDTO {
    private String nomeUsuario;
    private String mensagem;
    private String ticketId;

    public TicketResponseDTO(String nomeUsuario, String mensagem, String ticketId) {
        this.nomeUsuario = nomeUsuario;
        this.mensagem = mensagem;
        this.ticketId = ticketId;
    }
}
