package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Account;
import com.example.demo.service.AccountService;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping(path = "/newuser/signup")
	public ResponseEntity<Map<String, Object>> checkId(@RequestParam("user_id") String user_id) {
		if (user_id == null)
			return null;
		boolean success = accountService.checkId(user_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		return ResponseEntity.ok().body(map);
	}

	@PostMapping(path = "/newuser/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody Account account) {
		boolean success = accountService.signup(account);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		return ResponseEntity.ok().body(map);
	}

}
