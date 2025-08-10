package feign_demo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreaker registerCircuitBreakerEventLogger(CircuitBreakerRegistry registry) {
        CircuitBreaker cb = registry.circuitBreaker("userServiceBreaker");
        
        cb.getEventPublisher()
            .onStateTransition(event -> {
                System.out.println("### CircuitBreaker state changed: " + event.getStateTransition());
            })
            .onError(event -> {
                System.out.println("### CircuitBreaker error: " + event.getThrowable().toString());
            });
        
        return cb;  
    }
}