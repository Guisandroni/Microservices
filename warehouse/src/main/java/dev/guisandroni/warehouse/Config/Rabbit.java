package dev.guisandroni.store.Config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
}
