package com.example.controller;

import com.example.entity.MessageTaskTest;
import com.example.entity.ReceiverInformation;
import com.example.entity.ReceiverInformationBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//使用Post请求上传一组json数据   实例化为具体的消息对象    交给本地的    @PostMapping("/sendEmailbyrd")   来发送出去
//@PostMapping("/sendEmailbyrd")  接受消息对象     发送出去    其中  reciver是一个URL   返回的格式是{mail: "3309719563@qq.com"}
//{
//  "delivery_channel": "email", // 示例发送渠道
//  "sender_recipient": {
//    "phone": "+1234567890",
//    "email": "3309719563@qq.com",
//    "site_id": "system",
//  },
//  "receiver_query": "127.0.0.1:8088/hello        GET   ",
//  "receiver_information": {
 //       "needquery" :"false"
//    "phone": "15282149533",
//    "email": "3309719563@qq.com"
//  },
//  "template_id": 42,
//  "time_type": "now",
//  "status": "pending",
//  "alive_status": "true"
//}
@RestController
public class testupdatemsgController {
    @PostMapping("/whatapp")
    public ResponseEntity<String> sendMessage(@RequestBody MessageTaskTest messageTask) {
        try {

            // messageTaskTest对象处理    查看每一个字段是否填充完整  如果不完整的话   按照上面的示例模板填充
            //  private String deliveryChannel;
            //    //发送者的定义
            //    private SenderRecipient senderRecipient;
            //    //接受者回调查询
            //    private String receiverQuery;
            //    //接受者的定义
            //    private ReceiverInformation receiverInformation;
            //    //模板id
            //    private Integer templateId;
            //    //实际发送时间
            //    private String actualSendTime;
            //    //状态   为发送    已经发送   推送中
            //    private String status;
            //    //还发不发的存活状态
            //    private String aliveStatus;

            // 例如，调用邮件发送服务或其他业务逻辑
            // 假设处理成功，返回成功的响应
            if (messageTask.getDeliveryChannel() == null || messageTask.getDeliveryChannel().trim().isEmpty()) {
                messageTask.setDeliveryChannel("email");
            }
            if (messageTask.getReceiverQuery() == null || messageTask.getReceiverQuery().trim().isEmpty()) {
                messageTask.setReceiverQuery("127.0.0.1:8088/hello");
            }
            if (messageTask.getReceiverInformation() == null) {
                ReceiverInformationBuilder builder = new ReceiverInformationBuilder();
                // 可以根据需要设置字段，这里使用默认值
                builder.withNeedQuery("5555").withEmail("3309719563@qq.com").withPhone("15282149533");
                ReceiverInformation   rc = builder.build();
                messageTask.setReceiverInformation(rc);
            }
            ///调用邮件发送服务




            return ResponseEntity.ok("{ \"message\": \"邮件发送请求已接收\" }");
        } catch (Exception e) {
            // 处理异常，例如记录日志，并返回错误响应
            return ResponseEntity.badRequest().body("{ \"error\": \"" + e.getMessage() + "\" }");
        }
    }

}
