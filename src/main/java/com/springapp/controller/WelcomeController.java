package com.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
    @GetMapping("/")                // maps root
    public String home() {
        return "Hello from WelcomeController - Devyesh";
    }
	
	@GetMapping("/greet")
	public String welcome() {
		return "WelcomeController is called";
	}
}
