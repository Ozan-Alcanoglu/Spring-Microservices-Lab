package feign_demo.controller;

import feign_demo.feign.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final UserClient userClient;

    public ProductController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/products")
    public String getProducts() {
        return "Product list from product-service. Users: " + userClient.getUsers();
    }
}