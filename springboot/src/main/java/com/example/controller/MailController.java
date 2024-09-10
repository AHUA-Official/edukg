
package com.example.controller;

import com.example.entity.MessageTaskTest;
import com.example.entity.ReceiverInformation;
import com.example.entity.ReceiverInformationBuilder;
import com.example.service.MailService;
import com.example.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;


    @PostMapping("/sendEmail")
    public ResponseEntity<String>  sendEmail() {
        //15282149533
        String result = mailService.sendMail();
        if (result.startsWith("Email sent successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
