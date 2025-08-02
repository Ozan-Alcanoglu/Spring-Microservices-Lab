package feign_demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaConsumer {

    @KafkaListener(topics = "product-topic", groupId = "user-group")
    public void consumeMessage(String message) {
        System.out.println("Kafka'dan gelen mesaj (product-topic): " + message);
    }
}