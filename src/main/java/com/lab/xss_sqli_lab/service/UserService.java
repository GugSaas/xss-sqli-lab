package com.lab.xss_sqli_lab.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateProfileUnsafe(String cpf, String novoNome, String novoCelular) {
        if (novoNome == null || novoCelular == null) {
            throw new IllegalArgumentException("Nome e celular não podem ser nulos");
        }

        String sql = "UPDATE users SET nome = '" + novoNome + "', celular = '" + novoCelular + "' WHERE cpf = '" + cpf + "'";
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}