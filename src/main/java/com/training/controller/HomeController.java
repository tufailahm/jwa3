package com.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "---------Welcome to Revature APP --------";
	}
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome, Mohammad Tufail Ahmed.";
	}
}
