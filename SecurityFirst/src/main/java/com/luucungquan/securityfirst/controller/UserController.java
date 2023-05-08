package com.luucungquan.securityfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luucungquan.securityfirst.dto.Logindto;
import com.luucungquan.securityfirst.dto.RegisterDto;
import com.luucungquan.securityfirst.serivce.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	  @Autowired
	UserService userService;
	    
	  @PostMapping("/register")
	  public ResponseEntity<?>register(@RequestBody RegisterDto registerDto){
		  return userService.register(registerDto);
	  }
	  @PostMapping("/authentication")
	  public String authentication( @RequestBody Logindto logindto){
		  return userService.authentication(logindto);
	  }

}
