package com.luucungquan.securityfirst.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private long jwtExpiration;

// chuyển user về jwt
	public String generateToken(String username, List<String> roles) {

		return Jwts.builder().setSubject(username).claim("role", roles)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	// cái này trích xuất thông tin từ jwt sang user

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extracUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// cai nay duoc dung chung giua
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaim(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaim(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

//======================================
	// lấy thông tin của người dùng
	public String extracUserName(String token) {
	    if (StringUtils.isEmpty(token)) {
	        return null;
	    }
	    return extractClaim(token, Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);// cai la xem thoi gian co het han khong
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {}", e);
		} catch (MalformedJwtException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace: {}", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
			log.trace("Expired JWT token trace: {}", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token.");
			log.trace("Unsupported JWT token trace: {}", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
			log.trace("JWT token compact of handler are invalid trace: {}", e);
		}
		return false;
	}

}
