package feign_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired Environment environment;
	
    @GetMapping("/api/users")
    public String getUsers() {
        return "Response from port: " + environment.getProperty("local.server.port");
    }
}
