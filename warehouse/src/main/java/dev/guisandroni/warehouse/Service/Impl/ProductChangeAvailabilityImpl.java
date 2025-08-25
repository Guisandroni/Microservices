package dev.guisandroni.warehouse.Service.Impl;


import dev.guisandroni.warehouse.Dto.StockStatusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductChangeAvailabilityImpl implements dev.guisandroni.warehouse.Service.ProductChangeAvailability {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ProductChangeAvailabilityImpl(RabbitTemplate rabbitTemplate,
                                         @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                 String exchangeName,
                                         @Value("${spring.rabbitmq.routing-key.product-change-availability}")
                                                 String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }



    @Override
    public void notifyStatusChange(final StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
