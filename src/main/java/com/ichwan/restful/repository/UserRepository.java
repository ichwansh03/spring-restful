package com.ichwan.restful.repository;

import com.ichwan.restful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);
}
