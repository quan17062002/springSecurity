package com.luucungquan.securityfirst.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {// cái này nó sẽ sử dụng Header Authorization xem cái
																	// này có hợp lệ với người dùng không

	private final UserdetailService userdetailService;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {String token = getToken(request);
			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			    String usename = jwtTokenProvider.extracUserName(token);
			    UserDetails userDetails = userdetailService.loadUserByUsername(usename);
			    if (userDetails != null) {
			        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			                userDetails.getUsername(), null, userDetails.getAuthorities());
			        log.info("authenticated user with user :{}", usename);
			        SecurityContextHolder.getContext().setAuthentication(authentication);
			    }
			}
		filterChain.doFilter(request, response);
	}

	public String getToken(HttpServletRequest httpServletRequest) {
		final String bearerToken = httpServletRequest.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
