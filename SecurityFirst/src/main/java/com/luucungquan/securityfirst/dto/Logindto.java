package com.luucungquan.securityfirst.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data @AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class Logindto {
	private String username;
	private String password;

}
