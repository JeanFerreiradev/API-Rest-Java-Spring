package com.testemysql.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testemysql.teste.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
