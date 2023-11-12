package com.ichwan.restful.repository;

import com.ichwan.restful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
