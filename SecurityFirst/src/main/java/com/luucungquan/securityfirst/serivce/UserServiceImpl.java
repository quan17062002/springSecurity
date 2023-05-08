package com.luucungquan.securityfirst.serivce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luucungquan.securityfirst.dto.BearerToken;
import com.luucungquan.securityfirst.dto.Logindto;
import com.luucungquan.securityfirst.dto.RegisterDto;
import com.luucungquan.securityfirst.models.Role;
import com.luucungquan.securityfirst.models.RoleName;
import com.luucungquan.securityfirst.models.User;
import com.luucungquan.securityfirst.repository.RoleRepository;
import com.luucungquan.securityfirst.repository.UserRepository;
import com.luucungquan.securityfirst.security.JwtTokenProvider;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	 PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public User SaveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Role SaveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public String authentication(Logindto logindto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		List<String>roleName = new ArrayList<>();
		user.getRoles().forEach(r -> roleName.add(r.getRolename()) );
		String token = jwtTokenProvider.generateToken(user.getUsername(), roleName);
		return token;
	}

	@Override
	public ResponseEntity<?> register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("username is already taken ", HttpStatus.SEE_OTHER);// see other nếu có rồi nó
																							// sẽ đẩy ra ngoại 303
																							// chuyen huong
		} else {
			User user = new User();
			user.setFirstname(registerDto.getFirstname());
			user.setLastname(registerDto.getLastname());
			user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
			user.setUsername(registerDto.getUsername());
			Role role = roleRepository.findByRolename(RoleName.User);
			user.setRoles(Collections.singletonList(role));
			userRepository.save(user);
			String token = jwtTokenProvider.generateToken(registerDto.getUsername(),
					Collections.singletonList(role.getRolename()));
			// cai nay la truyen du lieu vao de no chuyen hoa sang dang jwt
			// no se chuyen sang jwttokenprovider
			return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
		}

	}
}
