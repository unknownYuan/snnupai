package me.snnupai.door.controller;

import me.snnupai.door.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;



    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public String sendMailMessage() {
//        MailMessage message = new MailMessage();
//
//        message.setMessageCode("MissingParameter");
//        message.setMessageStatus("Failed");
//        message.setCause("缺少参数,请确认");

        try {
//            mailService.sendMessageMail(message, "测试消息通知", "message.ftl");
            System.out.println("ok");
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
