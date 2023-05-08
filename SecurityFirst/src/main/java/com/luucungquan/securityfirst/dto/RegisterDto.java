package com.luucungquan.securityfirst.dto;

import java.util.List;

import com.luucungquan.securityfirst.models.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data @AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class RegisterDto {
	private String firstname;
	private String lastname;
	private String username;
	private String password;


}
