package dev.guisandroni.warehouse.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Rabbit {
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter (){
        return new Jackson2JsonMessageConverter();
    } //conversao json entre as classes

    @Bean
    RabbitTemplate rabbitTemplate (
            final ConnectionFactory connectionFactory,
            final Jackson2JsonMessageConverter converter
    ){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return  rabbitTemplate;
    } // config template para trabalhar com mensagens

    @Bean
    Queue queue(@Value("${spring.rabbitmq.queue.product-change-availability") final String name){
        return new Queue(name, true);
    }

    @Bean
    DirectExchange exchange(@Value("${spring.rabbitmq.queue.product-change-availability") final String name){
        return new DirectExchange(name);
    }

    @Bean
    Binding binding(final Queue queue,
                    final DirectExchange exchange,
                    @Value("${spring.rabbitmq.routing-key.product-change-availability}") final String name) {
        return BindingBuilder.bind(queue).to(exchange).with(name);
    }
}
