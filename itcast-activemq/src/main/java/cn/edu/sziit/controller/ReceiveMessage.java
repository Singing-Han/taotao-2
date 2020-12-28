package cn.edu.sziit.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component // 组件
public class ReceiveMessage {

    //接收队列的消息
    @JmsListener(destination = "taotao")
    public void receiveQueue(String data){
        System.out.println("ReceiverMessage接收队列的消息是：" + data);
    }


    //-----------------接收主题消息----------------------
    @JmsListener(destination = "NBA")
    public void receiveTopic01(String data){
        System.out.println("receiveTopic01接收队列的消息是：" + data);
    }

    @JmsListener(destination = "NBA")
    public void receiveTopic02(String data){
        System.out.println("receiveTopic02接收队列的消息是：" + data);
    }
    @JmsListener(destination = "NBA")
    public void receiveTopic03(String data){
        System.out.println("receiveTopic03接收队列的消息是：" + data);
    }

}
