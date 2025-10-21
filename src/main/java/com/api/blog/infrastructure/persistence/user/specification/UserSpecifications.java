package com.api.blog.infrastructure.persistence.user.specification;

import com.api.blog.module.user.domain.UserEntity;
import com.api.blog.module.user.domain.enumerator.RoleUser;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class UserSpecifications {

    public static Specification<UserEntity> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            return cb.like(cb.lower(root.get("name").as(String.class)), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<UserEntity> emailContains(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) return null;
            return cb.like(cb.lower(root.get("email").as(String.class)), "%" + email.toLowerCase() + "%");
        };
    }

    public static Specification<UserEntity> roleEquals(RoleUser role) {
        return (root, query, cb) -> {
            if (role == null) return null;
            return cb.equal(root.get("role"), role);
        };
    }

    public static Specification<UserEntity> createdBetween(Instant from, Instant to) {
        return (root, query, cb) -> {
            if (from == null && to == null) return null;
            var path = root.get("createAt");
            Instant start = (from != null) ? from : Instant.EPOCH;
            // Evita Instant.MAX (pode estourar alguns bancos). Usa "agora" como teto padrão.
            Instant end = (to != null) ? to : Instant.now();
            return cb.between(path, start, end);
        };
    }
}