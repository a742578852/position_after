//package com.justiceLeague.config;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    public static final String EXCHANGE_NAME = "location_exchange";
//    public static final String QUEUE_NAME = "location_queue";
//
//    /**
//     * 交换机
//     * @return
//     */
//    @Bean
//    public Exchange orderExchange() {
//        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
//        //return new TopicExchange(EXCHANGE_NAME, true, false);
//    }
//
//    /**
//     * 队列
//     * @return
//     */
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(QUEUE_NAME).build();
//        //return new Queue(QUEUE_NAME, true, false, false, null);
//    }
//    /**
//     * 交换机和队列绑定关系
//     */
//    @Bean
//    public Binding orderBinding(Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("location.#").noargs();
//        //return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "order.#", null);
//    }
//
//}
