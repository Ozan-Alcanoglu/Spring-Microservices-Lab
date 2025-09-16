package auth_demo.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	
	public static final String SECRET_KEY="uG7Vj9K8ZtQh5Lx2a9F3pB7sHkqYwN1vXmR6eT2oPfU=";

	public String generateToken(String username) {
				
		return Jwts.builder()
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
		.signWith(getKey(),SignatureAlgorithm.HS256)
		.compact();
	}
	
	
	public Key getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
	
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	
	public Object getClaimByKey(String token,String key) {
		
		Claims claims=getClaims(token);
		
		return claims.get(key);
		
	}

	
	public Claims getClaims(String token) {
		
		Claims claims=Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJwt(token).getBody();
		return claims;
	}
	
	
	public <T> T exportToken(String token , Function<Claims, T> claimsFunction) {
		Claims claims=getClaims(token);
		
		return claimsFunction.apply(claims);
	}
	
	
	public String getUserNameByToken(String token) {
		
		return exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		Date expiredDate=exportToken(token,Claims::getExpiration);
	
		return new Date().before(expiredDate);
	}
}