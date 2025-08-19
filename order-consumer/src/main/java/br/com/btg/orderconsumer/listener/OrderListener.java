package br.com.btg.orderconsumer.listener;

import br.com.btg.orderconsumer.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final OrderService orderService;

    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "${btg.rabbitmq.order.queue}")
    public void listen(Message<String> message) {
        logger.info("Message consumed: {}", message.getPayload());
        orderService.processarPedido(message.getPayload());
    }
}
