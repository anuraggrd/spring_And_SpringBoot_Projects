package com.embarkx.securityInMemory.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContoller {
	@GetMapping("embarkx/hello")
	public String hello() {
		return "Hello";
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("embarkx/user")
	public String userENdPoint() {
		return "Hello User";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("embarkx/admin")
	public String adminENdPoint() {
		return "Hello Admin !!";
	}

}
