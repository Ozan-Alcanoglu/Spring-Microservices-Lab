package feign_demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import feign_demo.entitiy.Product;

@Service
public class ProductKafkaProducer {

	private static final String TOPIC = "product-topic";
	
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("product-topic", message);
        System.out.println("Kafka'ya mesaj gönderildi: " + message);
    }
    
    public void sendProduct(Product product) {
        kafkaTemplate.send(TOPIC, product);
    }
}
