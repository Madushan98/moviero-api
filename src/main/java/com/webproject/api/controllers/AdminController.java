package com.webproject.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.repository.UserRepository;
import com.webproject.api.userLayer.UserService;


@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	UserService userService ;
	
	@Autowired
	UserRepository userRepository ;
	
	

@GetMapping	
public String GetUser() {
	
	return"users" ;
}


	

}
