package com.niranzan.tutor.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.niranzan.tutor.service.impl.UserPrinciple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Niranjan
 *
 */

@Component
public class JwtProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	@Value("${app.jwt.expiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(userPrinciple.getUsername());
		claims.put("id", userPrinciple.getId());
		claims.put("username", userPrinciple.getUsername());
		claims.put("email", userPrinciple.getEmail());
		claims.put("firstNm", userPrinciple.getFirstNm());
		claims.put("lastNm", userPrinciple.getLastNm());
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			LOGGER.error("Invalid JWT signature -> Message: {} ", e.getMessage());
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid JWT token -> Message: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOGGER.error("Expired JWT token -> Message: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOGGER.error("Unsupported JWT token -> Message: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty -> Message: {}", e.getMessage());
		}
		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}