package com.luucungquan.securityfirst.serivce;

import org.springframework.http.ResponseEntity;

import com.luucungquan.securityfirst.dto.Logindto;
import com.luucungquan.securityfirst.dto.RegisterDto;
import com.luucungquan.securityfirst.models.Role;
import com.luucungquan.securityfirst.models.User;

public interface UserService {
	User SaveUser(User user);
	Role SaveRole (Role role);
	String authentication(Logindto logindto);
	ResponseEntity<?>register(RegisterDto registerDto);

}
