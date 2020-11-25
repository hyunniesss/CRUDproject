package com.example.demo.service;

import com.example.demo.dto.Account;

public interface AccountService {
	
	boolean checkId(String user_id);
	boolean signup(Account account);

}
