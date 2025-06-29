package com.lab.xss_sqli_lab.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.xss_sqli_lab.dto.TicketCreateDTO;
import com.lab.xss_sqli_lab.dto.TicketResponseDTO;
import com.lab.xss_sqli_lab.model.User;
import com.lab.xss_sqli_lab.repository.UserRepository;
import com.lab.xss_sqli_lab.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserRepository userRepository;

    public TicketController(TicketService ticketService, UserRepository userRepository) {
        this.ticketService = ticketService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketCreateDTO dto) {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ticketService.createTicket(user.getId(), dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket criado com sucesso");
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<TicketResponseDTO>> getMyTickets() {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<TicketResponseDTO> tickets = ticketService.getTicketsForUser(user.getId());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTickets() {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getEmail().endsWith("@btgpactual.com")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
        }

        List<TicketResponseDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
}
