package feign_demo.controller;

import feign_demo.concretes.ProductService;
import feign_demo.entitiy.Product;
import feign_demo.feign.UserClient;
import feign_demo.kafka.ProductKafkaProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final UserClient userClient;
    private final ProductKafkaProducer producer;
    private final ProductService productService;

    public ProductController(UserClient userClient, ProductKafkaProducer producer,ProductService productService) {
        this.userClient = userClient;
        this.producer= producer;
        this.productService=productService;
    }

    @GetMapping("/productss")
    public String getProducts() {
        return "Product list from product-service. Users: " + userClient.getUsers();
    }
    
    @GetMapping("/test-api")
    public String getTestMessage() {
    	return "This is a product-service-api";
    }
    
    @GetMapping("/send")
    public String sendKafkaMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Mesaj Kafka'ya gönderildi: " + message;
    }
    
    @PostMapping("/send-product")
    public String sendProductToKafka(@RequestBody Product product) {
        producer.sendProduct(product);
        return "Product sent to Kafka!";
    }
    
    @GetMapping("/users")
    public String getUsersFromUserService() {
        return productService.getUsersFromUserService();
    }

}