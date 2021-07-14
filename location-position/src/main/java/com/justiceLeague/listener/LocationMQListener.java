//package com.justiceLeague.listener;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RabbitListener(queues = "location_queue")
//public class LocationMQListener {
//
//    /**
//     * RabbitHandler 会自动匹配 消息类型（消息自动确认）
//     * @param msg
//     * @param message
//     * @throws IOException
//     */
//    @RabbitHandler
//    public Message releaseCouponRecord(String msg, Message message) throws IOException {
//
//        long msgTag = message.getMessageProperties().getDeliveryTag();
//        System.out.println("msgTag="+msgTag);
//        System.out.println("message="+message.toString());
//        System.out.println("监听到消息：消息内容:"+message.getBody());
//
//        return message;
//    }
//}
