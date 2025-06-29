package com.lab.xss_sqli_lab.repository;

import com.lab.xss_sqli_lab.model.Ticket;
import com.lab.xss_sqli_lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findAllByUser(User user);
}
