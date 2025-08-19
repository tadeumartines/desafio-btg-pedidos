package br.com.btg.orderconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class AppConfig {

    @Value("${btg.rabbitmq.order.queue}")
    private String queueName;

    /**
     * Cria a fila no RabbitMQ automaticamente se ela não existir.
     * O 'true' torna a fila durável (não será perdida se o RabbitMQ reiniciar).
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    /**
     * Cria e disponibiliza um bean do ObjectMapper para toda a aplicação.
     * Necessário para converter JSON para objetos Java (e vice-versa).
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registra o módulo que sabe como lidar corretamente com tipos de data/hora do Java 8
        mapper.registerModule(new JavaTimeModule());
        // Garante que datas não sejam escritas como números (timestamps)
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    /**
     * Customiza o conversor do Spring Data MongoDB.
     * Este bean substitui o padrão, permitindo customizações como a remoção do campo _class.
     * Isso também resolve o ciclo de dependência que ocorria anteriormente.
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter(
            MongoDatabaseFactory factory,
            MongoMappingContext context
    ) {
        MappingMongoConverter converter = new MappingMongoConverter(factory, context);
        // Remove o campo "_class" que o Spring Data adiciona por padrão aos documentos
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
