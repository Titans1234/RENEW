package com.lti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.entity.User;
import com.lti.pojo.AdminLogin;
import com.lti.service.AdminService;

@CrossOrigin
@RestController
public class AdminRestController {

	@Autowired
	private AdminService service;

	@PostMapping(value = "/add", consumes = "application/json")
	public String addEmployee(@RequestBody Admin admin) {
		service.persist(admin);
		return "Employee added successfully";
	}

	@GetMapping(value = "/login", produces = "application/json")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

		AdminLogin login = new AdminLogin(username, password);
		return  service.adminLoginService(login);
		
	}
	
	
}
