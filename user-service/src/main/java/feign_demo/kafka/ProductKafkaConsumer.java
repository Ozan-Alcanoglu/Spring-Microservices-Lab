package feign_demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import feign_demo.entity.Product;

@Service
public class ProductKafkaConsumer {

    /*@KafkaListener(topics = "product-topic", groupId = "user-group")
    public void consumeMessage(String message) {
        System.out.println("Kafka'dan gelen mesaj (product-topic): " + message);
    }*/
    
    @KafkaListener(topics = "product-topic", groupId = "user-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeProduct(Product product) {
        System.out.println("Received Product: " + product.getName() + " - " + product.getPrice());
    }

}