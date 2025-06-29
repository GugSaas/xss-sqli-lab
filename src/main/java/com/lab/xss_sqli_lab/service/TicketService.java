package com.lab.xss_sqli_lab.service;


import com.lab.xss_sqli_lab.dto.TicketCreateDTO;
import com.lab.xss_sqli_lab.dto.TicketResponseDTO;
import com.lab.xss_sqli_lab.model.Ticket;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.repository.TicketRepository;
import com.lab.xss_sqli_lab.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createTicket(UUID userId, TicketCreateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Ticket ticket = new Ticket();
        ticket.setMensagem(dto.getMensagem());
        ticket.setUser(user);

        ticketRepository.save(ticket);
    }

    public List<TicketResponseDTO> getTicketsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return ticketRepository.findAllByUser(user).stream()
                .map(t -> new TicketResponseDTO(user.getNome(), t.getMensagem(), t.getId().toString()))
                .collect(Collectors.toList());
    }

    // admin
    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(t -> new TicketResponseDTO(t.getUser().getNome(), t.getMensagem(), t.getId().toString()))
                .collect(Collectors.toList());
    }
}
