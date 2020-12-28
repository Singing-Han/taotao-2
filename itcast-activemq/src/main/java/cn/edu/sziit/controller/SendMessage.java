package cn.edu.sziit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

public class SendMessage {




    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    //发送队列消息
    @RequestMapping("/queue")
    public String sendQueueMsg(){

        /**
         * 发送消息，
         * 参数一： 队列的名字
         * 参数二： 发送的消息内容
         */
        jmsMessagingTemplate.convertAndSend("taotao","该吃饭了~~");

        return "queue success~!~";
    }



    //发送主题消息
    @RequestMapping("/topic")
    public String sendTopicMsg(){

        /**
         * 发送消息，
         * 参数一： 队列的名字
         * 参数二： 发送的消息内容
         */
        jmsMessagingTemplate.convertAndSend("NBA","湖人队又夺得了2021年的总冠军~！~~");

        return "topic success~!~";
    }
}
