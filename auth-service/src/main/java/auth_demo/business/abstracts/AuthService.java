package auth_demo.business.abstracts;

import auth_demo.business.request.AuthRequest;
import auth_demo.business.response.AuthResponse;

public interface AuthService {
	
	public AuthResponse authenticate(AuthRequest authRequest);
	
	public String logout(String token);
	
}
