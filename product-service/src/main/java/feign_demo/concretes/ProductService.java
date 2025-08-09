package feign_demo.concretes;

import org.springframework.stereotype.Service;

import feign_demo.feign.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductService {

    private final UserClient userClient;

    public ProductService(UserClient userClient) {
        this.userClient = userClient;
    }

    @CircuitBreaker(name = "userServiceBreaker", fallbackMethod = "fallbackGetUsers")
    public String getUsersFromUserService() {
        return userClient.getUsers();
    }

    public String fallbackGetUsers(Throwable throwable) {
        return "User service şu anda yanıt vermiyor, lütfen daha sonra tekrar deneyin. (fallback) "
               + (throwable == null ? "" : "Reason: " + throwable.getClass().getSimpleName() + " - " + throwable.getMessage());
    }
}