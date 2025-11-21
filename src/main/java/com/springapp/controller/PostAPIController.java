package com.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.model.Employee;

@RestController
public class PostAPIController {

	 @PostMapping("/getuser")
	    public ResponseEntity<String> getUser(@RequestBody Employee employee) {
	        // simple echo of uid or whole object as JSON if you prefer
	        return ResponseEntity.ok(employee.getEid());
	    }
}