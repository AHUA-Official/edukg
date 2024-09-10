package com.example.entity;
//POJO
//示例如下    提供默认的初始化对象赋值方法
////{
////  "delivery_channel": "email", // 示例发送渠道
////  "sender_recipient": {
////    "phone": "+1234567890",
////    "email": "example@example.com",
////    "site_id": "system",
////  },
////  "receiver_query": "127.0.0.1:8088/hello        GET   ",
////  "receiver_information": {
// //       "needquery" :"true"
////    "phone": "15282149533",
////    "email": "3309719563@qq.com"
////  },
////  "template_id": 42,

////  "actual_send_time": null,
////  "status": "pending",

import java.util.Objects;

////  "alive_status": "true"
////}
public class MessageTaskTest {
    //发送渠道
    private String deliveryChannel = "email";
    //发送者的定义
    private SenderRecipient senderRecipient;
    //接受者回调查询
    private String receiverQuery = "127.0.0.1:8088/hello   GET ";
    //接受者的定义
    private ReceiverInformation receiverInformation;
    //模板id
    private Integer templateId  =42 ;
    //实际发送时间
    private String actualSendTime;
    //状态   为发送    已经发送   推送中
    private String status = "pending";
    //还发不发的存活状态
    private String aliveStatus  = "true";

    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    public void setDeliveryChannel(String deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
    }

    public SenderRecipient getSenderRecipient() {
        return senderRecipient;
    }

    public void setSenderRecipient(SenderRecipient senderRecipient) {
        this.senderRecipient = senderRecipient;
    }

    public String getReceiverQuery() {
        return receiverQuery;
    }

    public void setReceiverQuery(String receiverQuery) {
        this.receiverQuery = receiverQuery;
    }

    public ReceiverInformation getReceiverInformation() {
        return receiverInformation;
    }

    public void setReceiverInformation(ReceiverInformation receiverInformation) {
        this.receiverInformation = receiverInformation;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getActualSendTime() {
        return actualSendTime;
    }

    public void setActualSendTime(String actualSendTime) {
        this.actualSendTime = actualSendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAliveStatus() {
        return aliveStatus;
    }

    public void setAliveStatus(String aliveStatus) {
        this.aliveStatus = aliveStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageTaskTest that = (MessageTaskTest) o;
        return Objects.equals(deliveryChannel, that.deliveryChannel) &&
                Objects.equals(senderRecipient, that.senderRecipient) &&
                Objects.equals(receiverQuery, that.receiverQuery) &&
                Objects.equals(receiverInformation, that.receiverInformation) &&
                Objects.equals(templateId, that.templateId) &&
                Objects.equals(actualSendTime, that.actualSendTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(aliveStatus, that.aliveStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryChannel, senderRecipient, receiverQuery, receiverInformation, templateId, actualSendTime, status, aliveStatus);
    }
}

