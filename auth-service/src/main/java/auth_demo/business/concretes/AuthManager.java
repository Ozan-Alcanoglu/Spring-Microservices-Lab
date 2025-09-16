package auth_demo.business.concretes;

import org.springframework.stereotype.Service;

import auth_demo.business.abstracts.AuthService;
import auth_demo.business.request.AuthRequest;
import auth_demo.business.response.AuthResponse;
import auth_demo.jwt.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
	
	private final JwtService jwtService;

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		
		AuthResponse authResponse= new AuthResponse();
		
		String token =jwtService.generateToken(authRequest.getUsername());
		
		authResponse.setToken(token);
		
		return authResponse;
	}

	@Override
	public String logout(String token) {
        try {
        	
            jwtService.isTokenExpired(token);
            
            // normalde burada token blackliste eklenir 

            return "Logged out successfully";
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token");
        }
    }

}
