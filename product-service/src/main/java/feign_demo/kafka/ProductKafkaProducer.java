package feign_demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("product-topic", message);
        System.out.println("Kafka'ya mesaj gönderildi: " + message);
    }
}
