package com.luucungquan.securityfirst;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.luucungquan.securityfirst.models.Role;
import com.luucungquan.securityfirst.models.RoleName;
import com.luucungquan.securityfirst.models.User;
import com.luucungquan.securityfirst.repository.RoleRepository;
import com.luucungquan.securityfirst.repository.UserRepository;
import com.luucungquan.securityfirst.serivce.UserService;

@SpringBootApplication
public class SecurityFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityFirstApplication.class, args);
	}

}
