package com.example.emlakburadaauth.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.example.emlakburadaauth.model.AuthUser;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "emlakburada-secret-key-rfgd234ffr5";

	private static final long EXPIRATION_TIME = 300_000;

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	private Claims getAllClaimsFromToken(String token) {
		//// @formatter:off
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		// @formatter:on
	}

	public String getUserName(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Date getExpirationDate(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	public boolean isTokenExpired(String token) {
		return this.getExpirationDate(token).before(new Date());
	}

	public String generateToken(AuthUser user) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("email", user.getEmail());

		Map<String, Object> header = new HashMap<>();
		header.put("typ","JWT");

		long now = System.currentTimeMillis();

		// @formatter:off
 
		return Jwts
				.builder()
				.setHeader(header)
				.setClaims(claims)
				.setIssuedAt(new Date(now))
				.setSubject(user.getEmail())
				.setIssuer("emlakburada.com")
				.setExpiration( new Date(now + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		// @formatter:on
	}

}
