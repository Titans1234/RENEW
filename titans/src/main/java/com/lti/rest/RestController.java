package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.entity.Admin;
import com.lti.service.AdminService;

public class RestController {


	@Autowired
	private AdminService service;
	
	@PostMapping(value="/add", consumes = "application/json")
	public String addEmployee(@RequestBody Admin admin) {
		service.persist(admin);
		return "Employee added successfully";
	}
	
	@GetMapping(value = "/fetch/{id}", produces = "application/json")
	public Admin fetchEmployee(@PathVariable int id) {
		return service.find(id);
	}
}
