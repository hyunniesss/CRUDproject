package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUserId(String userId);

}