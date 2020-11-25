package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Account;
import com.example.demo.repo.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	public boolean checkId(String user_id) {
		Optional<Account> result = accountRepo.findByUserId(user_id);
		if (result.isPresent())
			return false;
		return true;
	}

	@Override
	public boolean signup(Account account) {
		if(account.getUserId().contains("admin")) {
			account.setUserRole("ADMIN");
		}else {
			account.setUserRole("USER");
		}
		Account result = accountRepo.save(account);
		if(result!=null)
			return true;
		return false;
	}

}
