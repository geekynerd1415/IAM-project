
package com.iamlab.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthenticationUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AuthenticationUser> findByEmail(String email) {
        String sql = """
            SELECT
                u.email,
                c.password_hash,
                c.enabled
            FROM users u
            JOIN user_credentials c ON c.user_id = u.id
            WHERE u.email = ?
            """;

        List<AuthenticationUser> results = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new AuthenticationUser(
                rs.getString("email"),
                rs.getString("password_hash"),
                rs.getBoolean("enabled")
            ),
            email
        );

        return results.stream().findFirst();
    }

    public record AuthenticationUser(
        String email,
        String passwordHash,
        boolean enabled
    ) {}
}
