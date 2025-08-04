	package feign_demo.controller;

import feign_demo.entitiy.Product;
import feign_demo.feign.UserClient;
import feign_demo.kafka.ProductKafkaProducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final UserClient userClient;
    private final ProductKafkaProducer producer;

    public ProductController(UserClient userClient, ProductKafkaProducer producer) {
        this.userClient = userClient;
        this.producer= producer;
    }

    @GetMapping("/products")
    public String getProducts() {
        return "Product list from product-service. Users: " + userClient.getUsers();
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
}