package com.lab.xss_sqli_lab.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @JdbcType(VarcharJdbcType.class)
    @Column(length = 36)
    private UUID id;

    @PrePersist
    public void generateId() {
        if (id == null) id = UUID.randomUUID();
    }

    @Column(nullable = false, length = 1000)
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
