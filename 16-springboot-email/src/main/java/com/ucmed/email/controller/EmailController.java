package com.ucmed.email.controller;

import com.ucmed.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("simple")
    public void sendSimpleMail() {
        emailService.sendSimpleMail();
    }

    @GetMapping("complicated")
    public void sendComplicated() throws MessagingException {
        emailService.sendComplicated();
    }
}
