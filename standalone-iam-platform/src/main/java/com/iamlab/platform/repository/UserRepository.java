package com.iamlab.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iamlab.platform.model.User;

@Repository
public class UserRepository {

        private final JdbcTemplate jdbcTemplate;

        public UserRepository(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        public User save(String name, String email) {
                String sql = """
                                INSERT INTO users (name, email)
                                VALUES (?, ?)
                                RETURNING id, name, email
                                """;

                return jdbcTemplate.queryForObject(
                                sql,
                                new UserRowMapper(),
                                name,
                                email);
        }

        public Optional<User> findById(Long id) {
                String sql = """
                                SELECT id, name, email
                                FROM users
                                WHERE id = ?
                                """;

                List<User> results = jdbcTemplate.query(
                                sql,
                                new UserRowMapper(),
                                id);

                return results.stream().findFirst();
        }

        public List<User> findAll() {

                String sql = """
                                SELECT id, name, email
                                FROM users
                                ORDER BY id
                                """;

                return jdbcTemplate.query(
                                sql,
                                new UserRowMapper());
        }

        public int update(Long id, String name, String email) {
                String sql = """
                                UPDATE users
                                SET name = ?, email = ?
                                WHERE id = ?
                                """;

                return jdbcTemplate.update(
                                sql,
                                name,
                                email,
                                id);
        }

        public int delete(Long id) {
                String sql = """
                                DELETE FROM users
                                WHERE id = ?
                                """;

                return jdbcTemplate.update(
                                sql,
                                id);
        }
}